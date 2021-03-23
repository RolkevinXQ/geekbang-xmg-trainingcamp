package org.rolkevin.configuration.microprofile.config.source.servlet;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextConfigInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //通过ServletContextListener将ServletContextConfigSource注册
        ServletContext servletContext = servletContextEvent.getServletContext();
        ServletContextConfigSource servletContextConfigSource = new ServletContextConfigSource(servletContext);

        //当前classloader
        ClassLoader classLoader = servletContext.getClassLoader();

        //内部通过spi方式，得到ConfigProviderResolver
        //services/org.eclipse.microprofile.config.spi.ConfigProviderResolver
        ConfigProviderResolver configProviderResolver = ConfigProviderResolver.instance();

        //创建ConfigBuilder
        ConfigBuilder configBuilder = configProviderResolver.getBuilder();

        configBuilder.forClassLoader(classLoader);

        configBuilder.addDefaultSources();

        configBuilder.addDiscoveredConverters();

        //额外的拓展配置源
        configBuilder.withSources(servletContextConfigSource);

        //得到Config
        Config config = configBuilder.build();

        //注册config，并关联到当前classloader
        configProviderResolver.registerConfig(config,classLoader);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
