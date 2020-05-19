package my.learning.jdbc.demo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MyJDBCTemplate {

    private DataSource dataSource;

    public MyJDBCTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> queryForList(String sql, Class<T> cls, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //1.注册驱动(已解决)
            //2.获取连接（已解决）
            conn = dataSource.getConnection();
            //3.执行sql，封装参数（已解决）
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= args.length; i++) {
                ps.setObject(i, args[i - 1]);
            }
            rs = ps.executeQuery();

            //4.封装结果集（已解决）
            List<T> accounts = BeanUtils.populateList(rs, cls);
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.释放资源 4.写法繁琐（已解决），频繁释放资源（已解决）
            JDBCUtils2.close(rs, ps, conn);
        }
        return null;
    }
}
