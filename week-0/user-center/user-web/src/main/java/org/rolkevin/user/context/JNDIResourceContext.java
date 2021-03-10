package org.rolkevin.user.context;



import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import java.util.NoSuchElementException;

public class JNDIResourceContext {

    public static final String CONTEXT_NAME = "JNDIContext";
    private Context namingContext;

    private static ServletContext servletContext;
    public void setServletContext(ServletContext servletContext) {
        servletContext.setAttribute(CONTEXT_NAME,this);
        JNDIResourceContext.servletContext = servletContext;

    }

   /**
    * 通过ServletContext#getAttribute()获取JNDIResourceContext
    * 整个应用上下文只需实例化一次
    * @return
    */
   public static JNDIResourceContext getInstance(){
        return (JNDIResourceContext)servletContext.getAttribute(CONTEXT_NAME);
   }

    /**
     * 初始化Context
     * @throws RuntimeException
     */
    public void initResourceContext() throws RuntimeException{
        try {
            Context context = new InitialContext();
            this.namingContext = (Context) context.lookup("java:comp/env");
        }catch (NamingException e){
            throw  new RuntimeException(e);
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


}
