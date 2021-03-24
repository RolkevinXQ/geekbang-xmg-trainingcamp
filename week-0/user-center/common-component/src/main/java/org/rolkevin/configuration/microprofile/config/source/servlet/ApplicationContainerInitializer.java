package org.rolkevin.configuration.microprofile.config.source.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


public interface ApplicationContainerInitializer {

    //String INITIALIZER_ORDINAL = "container_ordinal";
    //int DEFAULT_ORDINAL = 0;

    public void onStartup(ServletContext servletContext) throws ServletException;

    default int getOrdinal() {
        return 0;
    }


}
