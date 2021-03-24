package org.rolkevin.configuration.microprofile.config.source.servlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

public class ApplicationServletContainerInitializer implements ServletContainerInitializer {

    private List<ApplicationContainerInitializer> initializerList = new LinkedList<>();

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {

        ServiceLoader<ApplicationContainerInitializer>  applicationContainer = ServiceLoader.load(ApplicationContainerInitializer.class,servletContext.getClassLoader());

        if (applicationContainer != null){
            applicationContainer.forEach(a->{
                initializerList.add(a);
            });
        }

        if (initializerList.isEmpty()){
            servletContext.log("没有找到容器初始化配置");
        }else{
            //排序
            initializerList.sort(ApplicationContainerInitializerComparator.INSTANCE);
            for(ApplicationContainerInitializer a: initializerList){
                a.onStartup(servletContext);
            }
        }

    }
}
