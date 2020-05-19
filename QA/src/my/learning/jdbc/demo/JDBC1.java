package my.learning.jdbc.demo;


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
            //所有的url的格式：“协议名://主机名(IP):端口/资源路径”
            // getConnection封装的TCP底层协议（JDBC是TCP的封装）
            // 第二个问题：直接硬编码了把用户名和密码直接写出来？
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "123456");
            String sql = "select * from account where id = ?";  //用占位符防止SQL注入

            //3.执行sql
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 2);
            rs = ps.executeQuery();

            //4.封装结果集
            // 第三个问题：封装对象比较单一，程序扩展性差
            Account account = new Account();
            while (rs.next()) {
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                account.setMoney(rs.getFloat("money"));
            }
            System.out.println(account);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //5.释放资源
            // 第四个问题：释放资源太繁琐，频繁申请释放资源很浪费资源
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
