package my.learning.jdbc.demo;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String url;
    private static String username;
    private static String password;
    private static String dirver;

    /**
     * 配置文件的读取只需要读取一次
     */
    static {
        Properties pro = new Properties();
        try {
            //加载配置文件
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            pro.load(classLoader.getResourceAsStream("pro.properties"));
            //从配置文件中读取配置信息
            url = pro.getProperty("url");
            username = pro.getProperty("user");
            password = pro.getProperty("password");
            dirver = pro.getProperty("driver");
            //加载数据库驱动
            Class.forName(dirver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
        Connection conn = DriverManager.getConnection(url, username, password);
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
}
