package org.rolkevin.user.sql;

import org.rolkevin.user.context.JNDIResourceContext;
import org.rolkevin.user.domain.User;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnectionManagerFactory {

    private static Logger logger = Logger.getLogger(DBConnectionManagerFactory.class.getName());

    public DBConnectionManagerFactory(){
        //initDataBase();
    }

    @Resource(name = "jdbc/UserCenterDB")
    private DataSource dataSource;

    @Resource(name = "bean/entityManager")
    private EntityManager entityManager;


    /*
    @Deprecated
    public Connection getConnection() {
        JNDIResourceContext context = JNDIResourceContext.getInstance();
        Connection connection = null;
        try {
            DataSource dataSource = context.getResource("jdbc/UserCenterDB");
            connection = dataSource.getConnection();
        }catch (SQLException e){
            logger.log(Level.SEVERE,"数据库连接获取失败",e);
        }
        if (connection != null){
            logger.log(Level.INFO,"数据库连接获取成功");
        }
        return connection;
    }*/

    /** 通过注入的DataSource获取连接
     * @since 1.1
     * @return
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            //DataSource dataSource = context.getResource("jdbc/UserCenterDB");
            connection = dataSource.getConnection();
        }catch (SQLException e){
            logger.log(Level.SEVERE,"数据库连接获取失败",e);
        }
        if (connection != null){
            logger.log(Level.INFO,"数据库连接获取成功");
        }
        return connection;
    }

    public EntityManager getEntityManager() {
        logger.info("当前 EntityManager 实现类：" + entityManager.getClass().getName());
        return entityManager;
    }

    public void releaseConnection() {
//        if (this.connection != null) {
//            try {
//                this.connection.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e.getCause());
//            }
//        }
    }

    public void initDBConnectionManagerByJNDI(){
        try {
            Context initCtx = new InitialContext();
            Context envContext = (Context) initCtx.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/UserCenterDB");
            Connection connection = dataSource.getConnection();
        }catch (Exception e){
            logger.log(Level.INFO,"数据库JNDI加载失败，信息：%s",e.getMessage());
        }
    }

    private void initDataBase() {
        Connection connection = getConnection();

        Statement statement = null;
        try{
            statement = connection.createStatement();
            // 删除 users 表
            System.out.println(statement.execute(DROP_USERS_TABLE_DDL_SQL));
        }catch (Exception e){
            System.err.println("删除数据库表："+e.getMessage());
        }
        try{
            // 创建 users 表
            System.out.println(statement.execute(CREATE_USERS_TABLE_DDL_SQL));
        }catch (Exception e){
            System.err.println("初始化数据库表："+e.getMessage());
        }

    }

    public static final String DROP_USERS_TABLE_DDL_SQL = "DROP TABLE users ";

    public static final String CREATE_USERS_TABLE_DDL_SQL = "CREATE TABLE users(" +
            "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "name VARCHAR(16) NOT NULL, " +
            "password VARCHAR(64) NOT NULL, " +
            "email VARCHAR(64) NOT NULL, " +
            "phoneNumber VARCHAR(64) " +
            ")";

    public static final String INSERT_USER_DML_SQL = "INSERT INTO users(name,password,email,phoneNumber) VALUES " +
            "('A','123','a@gmail.com','1') , " +
            "('B','123','b@gmail.com','2') , " +
            "('C','123','c@gmail.com','3') , " +
            "('D','123','d@gmail.com','4') , " +
            "('E','123','e@gmail.com','5')";
}
