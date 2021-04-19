package org.rolkevin.configuration.microprofile.config.servlet.listener;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class ConfigServletRequestListener implements ServletRequestListener {

    private static final ThreadLocal<Config> CONFIG_THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        ServletContext servletContext = servletRequest.getServletContext();
        ClassLoader classLoader = servletContext.getClassLoader();
        ConfigProviderResolver configProviderResolver = ConfigProviderResolver.instance();
        Config config = configProviderResolver.getConfig();
        CONFIG_THREAD_LOCAL.set(config);
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        CONFIG_THREAD_LOCAL.remove();
    }

    public static Config getConfig(){
        return CONFIG_THREAD_LOCAL.get();
    }
}
