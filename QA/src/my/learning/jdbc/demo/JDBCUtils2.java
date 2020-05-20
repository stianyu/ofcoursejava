package my.learning.jdbc.demo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 注入了Druid连接池的JDBCUtil工具类
 * 使用数据库连接池，解决了频繁申请、释放资源对资源的浪费，且统一管理响应速率高
 */
public class JDBCUtils2 {
    //连接池成员变量
    private static DataSource ds;

    /**
     * 配置文件的读取只需要读取一次
     */
    static {
        Properties pro = new Properties();
        try {
            //加载配置文件
            ClassLoader classLoader = JDBCUtils2.class.getClassLoader();
            //从配置文件中读取配置信息
            pro.load(classLoader.getResourceAsStream("pro.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
            //加载数据库驱动
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * 采用配置文件避免传参
     *
     * @return 连接对象
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = ds.getConnection();
        return conn;
    }

    /**
     * 释放资源(DML)
     *
     * @param stmt
     * @param conn
     */
    public static void close(Statement stmt, Connection conn) {
        if (stmt != null) { //避免空指针异常
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) { //避免空指针异常
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源（DQL）
     *
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) { //避免空指针异常
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) { //避免空指针异常
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) { //避免空指针异常
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DataSource getDs() {
        return ds;
    }
}
