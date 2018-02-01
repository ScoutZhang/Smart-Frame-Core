package org.smart4j.smart.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * 基于JDBC
 * Created by DEAN on 2018/1/11.
 */
public class DatabaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);

    private static final String url = ConfigHelper.getJdbcUrl();
    private static final String username = ConfigHelper.getJdbcUsername();
    private static final String password = ConfigHelper.getJdbcPassword();

    private static ThreadLocal<Connection> connContainer = new ThreadLocal<Connection>();

    static {
        try{
            Class.forName(ConfigHelper.getJdbcDriver());
        }catch(ClassNotFoundException e){
            LOGGER.error("class not found", e);
            throw new RuntimeException(e);
        }
    }

    //获得数据库连接
    private static Connection getConnection() throws Exception{
        Connection conn = connContainer.get();
        if(conn==null){
            conn = DriverManager.getConnection(url,username,password);
            connContainer.set(conn);
        }
        return conn;
    }

    //开启事务
    public static void beginTransaction() throws Exception{
        Connection conn = getConnection();
        if(conn!=null){
          try{
              conn.setAutoCommit(false);
          }catch (SQLException e){
              LOGGER.error("begin transaction failure", e);
              throw new RuntimeException(e);
          }
        }
    }

    //提交事务
    public static void commitTransaction() throws Exception{
        Connection conn = getConnection();
        if(conn!=null){
            try{
                conn.commit();
                conn.close();
            }catch(SQLException e){
                LOGGER.error("commit transaction failure", e);
                throw new RuntimeException(e);
            }
        }
    }

    //回滚事务
    public static void rollbackTransaction() throws Exception{
        Connection conn = getConnection();
        if(conn!=null){
            try {
                conn.rollback();
                conn.close();
            }catch (SQLException e){
                LOGGER.error("rollback transaction failure", e);
                throw new RuntimeException(e);
            }
        }
    }

    //SQL语句执行（未关闭Statement和ResultSet）
    public static ResultSet executeSql(String sql) throws Exception{
        Statement st = getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }
}
