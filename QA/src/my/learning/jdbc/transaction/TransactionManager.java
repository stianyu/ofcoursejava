package my.learning.jdbc.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 和事务管理相关的工具类
 * 包含：开启事务、提交事务、回滚事务、释放连接
 */
public class TransactionManager {
    private ConnectionUtils connectionUtils;

    //使用spring注入
    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    //开始事务
    public void beginTransaction(){
        Connection conn = connectionUtils.getThreadConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //提交事务
    public void commit(){
        Connection conn = connectionUtils.getThreadConnection();
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //回滚事务
    public void rollback(){
        Connection conn = connectionUtils.getThreadConnection();
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //释放连接
    public void release(){
        Connection conn = connectionUtils.getThreadConnection();
        try {
            conn.close();//这里是归还连接到连接池
            connectionUtils.removeConnection();//将线程和连接解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
