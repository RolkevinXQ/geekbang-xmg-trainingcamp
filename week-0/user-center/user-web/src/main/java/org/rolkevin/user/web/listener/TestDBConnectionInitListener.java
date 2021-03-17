package org.rolkevin.user.web.listener;

import org.rolkevin.configuration.microprofile.config.JavaConfig;
import org.rolkevin.user.context.ComponentContext;
import org.rolkevin.user.domain.User;
import org.rolkevin.user.management.UserManager;
import org.rolkevin.user.sql.DBConnectionManagerFactory;

import javax.management.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.util.logging.Level;
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

        createMXBean();

        testConfing();


    }


    /**
     * 注册JMX Bean
     */
    private void createMXBean() {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = null;
        try {
            objectName = new ObjectName("org.rolkevin.user.management.MBeanDemo:type=MBeanDemo");
            User user = new User();
            user.setName("XQ");
            user.setEmail("xq@foxmail.com");
            user.setPhoneNumber("10086");

            UserManager userManager = new UserManager(user);
            mBeanServer.registerMBean(userManager,objectName);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

    }

    /**
     * 读取本地配置
     */
    private void testConfing() {
        JavaConfig config = new JavaConfig();
        String value = config.getValue("application.name",String.class);
        logger.log(Level.INFO,"本地配置-application.name = "+value);
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
