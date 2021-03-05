package org.rolkevin.user.web.listener;

import org.rolkevin.user.context.JNDIResourceContext;
import org.rolkevin.user.sql.DBConnectionManagerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;


/**
 * 测试
 */
@Deprecated
public class TestDBConnectionInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        JNDIResourceContext context = JNDIResourceContext.getInstance();
        DBConnectionManagerFactory managerFactory = context.getResource("bean/DBConnectionManagerFactory");
        Connection connection = managerFactory.getConnection();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
