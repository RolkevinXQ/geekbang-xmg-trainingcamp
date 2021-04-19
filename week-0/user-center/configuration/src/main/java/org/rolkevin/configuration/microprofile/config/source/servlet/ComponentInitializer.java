package org.rolkevin.configuration.microprofile.config.source.servlet;

import org.rolkevin.user.context.ComponentContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ComponentInitializer implements ApplicationContainerInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ComponentContext context = new ComponentContext();
        context.initComponentContext(servletContext);
    }

    @Override
    public int getOrdinal() {
        return 1;
    }
}
