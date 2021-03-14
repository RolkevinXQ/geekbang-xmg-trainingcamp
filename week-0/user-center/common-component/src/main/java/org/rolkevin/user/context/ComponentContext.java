package org.rolkevin.user.context;

import org.rolkevin.function.ThrowableAction;
import org.rolkevin.function.ThrowableFunction;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.naming.*;
import javax.servlet.ServletContext;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ComponentContext {

    public static final String CONTEXT_NAME = ComponentContext.class.getName();
    private static final Logger logger = Logger.getLogger(CONTEXT_NAME);

    private Context namingContext;
    private Map<String, Object> componentsMap = new LinkedHashMap<>();
    private ClassLoader classLoader;

    private static ServletContext servletContext;
    public void setServletContext(ServletContext servletContext) {
        servletContext.setAttribute(CONTEXT_NAME,this);
        ComponentContext.servletContext = servletContext;
    }

    /**
     * 初始化上下文
     * @param servletContext
     * @since 1.1
     */
    public void initComponentContext(ServletContext servletContext){
        servletContext.setAttribute(CONTEXT_NAME,this);
        ComponentContext.servletContext = servletContext;
        classLoader = servletContext.getClassLoader();
        //初始化JNDI容器
        initEnvContext();

        //实例化组件
        instantiateComponents();

        //初始化组件
        initializeComponents();


    }

    /**
     * 初始化jndiEnvContxt
     * @throws RuntimeException
     * @since 1.1
     */
    private void initEnvContext() throws RuntimeException{
        Context context = null;
        try {
            if (this.namingContext == null) {
                context = new InitialContext();
                this.namingContext = (Context) context.lookup("java:comp/env");
            }
        }catch (NamingException e){
            throw  new RuntimeException(e);
        }finally {
            closeContext(context);
        }
    }

    /**
     * 实例化组件
     */
    protected void instantiateComponents() {
        // 遍历获取所有的组件名称
        List<String> componentNames = listAllComponentNames();
        // 通过依赖查找，实例化对象（ Tomcat BeanFactory setter 方法的执行，仅支持简单类型）
        componentNames.forEach(name -> componentsMap.put(name, lookupComponent(name)));
    }

    private List<String> listAllComponentNames() {
        return listComponentNames("/");
    }

    protected List<String> listComponentNames(String name) {
        return executeInContext(context -> {
            NamingEnumeration<NameClassPair> e = executeInContext(context, ctx -> ctx.list(name), true);

            // 目录 - Context
            // 节点 -
            if (e == null) { // 当前 JNDI 名称下没有子节点
                return Collections.emptyList();
            }

            List<String> fullNames = new LinkedList<>();
            while (e.hasMoreElements()) {
                NameClassPair element = e.nextElement();
                String className = element.getClassName();
                Class<?> targetClass = classLoader.loadClass(className);
                if (Context.class.isAssignableFrom(targetClass)) {
                    // 如果当前名称是目录（Context 实现类）的话，递归查找
                    fullNames.addAll(listComponentNames(element.getName()));
                } else {
                    // 否则，当前名称绑定目标类型的话话，添加该名称到集合中
                    String fullName = name.startsWith("/") ?
                            element.getName() : name + "/" + element.getName();
                    fullNames.add(fullName);
                }
            }
            return fullNames;
        });
    }

    /**
     * 在 Context 中执行，通过指定 ThrowableFunction 返回计算结果
     *
     * @param function ThrowableFunction
     * @param <R>      返回结果类型
     * @return 返回
     * @see ThrowableFunction#apply(Object)
     */
    protected <R> R executeInContext(ThrowableFunction<Context, R> function) {
        return executeInContext(function, false);
    }

    /**
     * 在 Context 中执行，通过指定 ThrowableFunction 返回计算结果
     *
     * @param function         ThrowableFunction
     * @param ignoredException 是否忽略异常
     * @param <R>              返回结果类型
     * @return 返回
     * @see ThrowableFunction#apply(Object)
     */
    protected <R> R executeInContext(ThrowableFunction<Context, R> function, boolean ignoredException) {
        return executeInContext(this.namingContext, function, ignoredException);
    }

    private <R> R executeInContext(Context context, ThrowableFunction<Context, R> function,
                                   boolean ignoredException) {
        R result = null;
        try {
            result = ThrowableFunction.execute(context, function);
        } catch (Throwable e) {
            if (ignoredException) {
                logger.warning(e.getMessage());
            } else {
                throw new RuntimeException(e);
            }
        }
        return result;
    }


   /**
    * 通过ServletContext#getAttribute()获取JNDIResourceContext
    * 整个应用上下文只需实例化一次
    * @return
    */
   public static ComponentContext getInstance(){
        return (ComponentContext)servletContext.getAttribute(CONTEXT_NAME);
   }

    protected <C> C lookupComponent(String name) {
        return executeInContext(context -> (C) context.lookup(name));
    }

    /**
     * 初始化Context
     * @throws RuntimeException
     * @since 1.0
     */
    public void initResourceContext() throws RuntimeException{
        try {
            Context context = new InitialContext();
            this.namingContext = (Context) context.lookup("java:comp/env");
        }catch (NamingException e){
            throw  new RuntimeException(e);
        }
    }


    /**
     * 初始化组件（支持 Java 标准 Commons Annotation 生命周期）
     * <ol>
     *  <li>注入阶段 - {@link Resource}</li>
     *  <li>初始阶段 - {@link PostConstruct}</li>
     *  <li>销毁阶段 - {@link PreDestroy}</li>
     * </ol>
     */
    protected void initializeComponents() {
        componentsMap.values().forEach(component -> {
            Class<?> componentClass = component.getClass();
            // 注入阶段 - {@link Resource}
            injectComponents(component, componentClass);
            // 初始阶段 - {@link PostConstruct}
            processPostConstruct(component, componentClass);

        });
    }

    private void injectComponents(Object component, Class<?> componentClass) {
        Stream.of(componentClass.getDeclaredFields())
                .filter(field -> {
                    int mods = field.getModifiers();
                    return !Modifier.isStatic(mods) &&
                            field.isAnnotationPresent(Resource.class);
                }).forEach(field -> {
            Resource resource = field.getAnnotation(Resource.class);
            String resourceName = resource.name();
            Object injectedObject = lookupComponent(resourceName);
            field.setAccessible(true);
            try {
                // 注入目标对象
                field.set(component, injectedObject);
            } catch (IllegalAccessException e) {
            }
        });
    }

    private void processPostConstruct(Object component, Class<?> componentClass) {
        Stream.of(componentClass.getMethods())
                .filter(method ->
                        !Modifier.isStatic(method.getModifiers()) &&      // 非 static
                                method.getParameterCount() == 0 &&        // 没有参数
                                method.isAnnotationPresent(PostConstruct.class) // 标注 @PostConstruct
                ).forEach(method -> {
            // 执行目标方法
            try {
                method.invoke(component);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void processPreDestroy() {
        // TODO
    }

    private void closeContext(Context context){
        if (context!=null){
            ThrowableAction.execute(context::close);
        }
    }


    public void destroy() throws RuntimeException{
        if (this.namingContext != null){
            try{
                namingContext.close();
                servletContext.log("应用关闭时，JNDI上下文资源关闭");
            }catch (NamingException e){
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 通过名称进行JNDI依赖查找
     * @param jndiName
     * @param <R>
     * @return
     */
    public <R> R getResource(String jndiName){
        R resource = null;
        try {
            resource = (R)this.namingContext.lookup(jndiName);
        }catch (NamingException e){
            throw  new NoSuchElementException(jndiName);
        }
        return  resource;
    }

    /**
     * 通过名称进行依赖查找
     *
     * @param name
     * @param <C>
     * @return
     */
    public <C> C getComponent(String name) {
        return (C) componentsMap.get(name);
    }

    /**
     * 获取所有的组件名称
     *
     * @return
     */
    public List<String> getComponentNames() {
        return new ArrayList<>(componentsMap.keySet());
    }


}
