package org.rolkevin.user.web.listener;

import org.rolkevin.user.context.JNDIResourceContext;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.logging.Level;

public class DBConnectionInitListener implements ServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.getServletContext();
        JNDIResourceContext jndiResourceContext = new JNDIResourceContext();
        jndiResourceContext.initResourceContext();
        jndiResourceContext.setServletContext(this.servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        JNDIResourceContext context = JNDIResourceContext.getInstance();
        context.destroy();
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Context envContext = (Context) new InitialContext().lookup("java:comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/UserCenterDB");
            connection = dataSource.getConnection();
        }catch (Exception e){
            servletContext.log("数据库JNDI加载失败，信息：%s",e);
        }
        if (connection !=null){
            servletContext.log("数据库JNDI获取成功");
        }
        return connection;
    }
}
