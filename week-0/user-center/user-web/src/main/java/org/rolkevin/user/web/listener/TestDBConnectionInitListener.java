package org.rolkevin.user.web.listener;

import org.rolkevin.user.context.ComponentContext;
import org.rolkevin.user.context.JNDIResourceContext;
import org.rolkevin.user.domain.User;
import org.rolkevin.user.sql.DBConnectionManagerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.logging.Logger;


/**
 * 测试
 */
@Deprecated
public class TestDBConnectionInitListener implements ServletContextListener {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        JNDIResourceContext context = JNDIResourceContext.getInstance();
//        DBConnectionManagerFactory managerFactory = context.getResource("bean/DBConnectionManagerFactory");
//        Connection connection = managerFactory.getConnection();
        ComponentContext context = ComponentContext.getInstance();
        DBConnectionManagerFactory managerFactory = context.getComponent("bean/DBConnectionManagerFactory");
        Connection connection = managerFactory.getConnection();
        //testUser(managerFactory.getEntityManager());
        logger.info("所有的 JNDI 组件名称：[");
        context.getComponentNames().forEach(logger::info);
        logger.info("]");


    }

    private void testUser(EntityManager entityManager) {
        User user = new User();
        user.setName("小马哥");
        user.setPassword("12345");
        user.setEmail("mercyblitz@gmail.com");
        user.setPhoneNumber("abcdefg");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        System.out.println(entityManager.find(User.class, user.getId()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
