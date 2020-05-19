package my.learning.jdbc.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接工具类，用于从数据源获取一个连接，并且实现和线程绑定
 * 在DAO和数据源之间起到一个代理作用，DAO不再从数据源直接拿连结，而是从ConnectionUtils中拿
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> tl = new ThreadLocal<>();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     *
     * @return
     */
    public Connection getThreadConnection() {
        try {
            //1.先从ThreadLocal上获取
            Connection conn = tl.get();
            //2.判断当前线程上是否有连接
            if (conn == null) {
                //3.从数据源获取一个连接，并且存入ThreadLocal
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            //4.返回当前线程上的连接
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //对于web应用，线程和连接使用完毕后分别会被归还到线程池和连接池，为了保证下次能够正常使用连接需要将二者解绑
    public void removeConnection(){
        tl.remove();
    }
}
