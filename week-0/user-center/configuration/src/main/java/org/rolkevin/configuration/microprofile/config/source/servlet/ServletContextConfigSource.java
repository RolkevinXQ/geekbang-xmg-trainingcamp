package org.rolkevin.configuration.microprofile.config.source.servlet;

import org.rolkevin.configuration.microprofile.config.source.MapBasedConfigSource;

import javax.servlet.ServletContext;
import java.util.Enumeration;
import java.util.Map;

/**
 * Servlet配置源
 */
public class ServletContextConfigSource extends MapBasedConfigSource {

    private final ServletContext servletContext;

    public ServletContextConfigSource(ServletContext servletContext) {
        super("ServletContext Init Parameters", 500,false);
        this.servletContext = servletContext;
        getProperties();
    }

    @Override
    protected void prepareConfigData(Map configData) throws Throwable {
        Enumeration<String> parameterNames = servletContext.getInitParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            configData.put(parameterName, servletContext.getInitParameter(parameterName));
        }
    }
}
