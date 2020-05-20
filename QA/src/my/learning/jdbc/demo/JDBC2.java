package my.learning.jdbc.demo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC2 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //1.注册驱动(已解决)
            //2.获取连接（已解决）
            conn = JDBCUtils.getConnection();
            String sql = "select * from account where id = ?";

            //3.执行sql
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 2);
            rs = ps.executeQuery();

            //4.封装结果集
            Account account = new Account();
            while (rs.next()) {//3.封装逻辑比较单一，无法扩展
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                account.setMoney(rs.getFloat("money"));
            }
            System.out.println(account);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.释放资源 4.写法繁琐（已解决），频繁释放资源
            JDBCUtils.close(rs, ps, conn);
        }
    }
}
