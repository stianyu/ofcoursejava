package my.learning.jdbc.bean;


import my.learning.jdbc.bean.Account;

import java.sql.*;

/**
 * JDBC的入门程序
 */
public class JDBC1 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");  //为什么使用Class.forName而不使用下面的registerDriver
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db2", "root", "123456");
            String sql = "SELECT account.*,user.username FROM account INNER JOIN USER ON account.uid = user.id AND account.id = ?";  //用占位符防止SQL注入
            String sql2 = "SELECT account.id aid, account.`money`,user.* FROM account INNER JOIN USER ON account.uid = user.id AND account.id = ?";  //用占位符防止SQL注入

            //3.执行sql
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, 2);
            rs = ps.executeQuery();

            //4.封装结果集
            // 查找多对一
            Account account = new Account();
            User user = new User();
            while (rs.next()) {
                account.setId(rs.getInt("aid"));
                account.setUid(rs.getInt("id"));
                account.setMoney(rs.getFloat("money"));
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                account.setUser(user);
            }
            System.out.println(account);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //5.释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
