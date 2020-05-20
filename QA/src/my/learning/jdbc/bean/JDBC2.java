package my.learning.jdbc.bean;


import my.learning.jdbc.dao.AccountDaoTest;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC的入门程序
 */
public class JDBC2 {

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
            String sql = "SELECT account.id aid, account.`money`,user.* FROM account INNER JOIN USER ON account.uid = user.id AND user.id = ?";  //用占位符防止SQL注入

            //3.执行sql
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 2);
            rs = ps.executeQuery();

            //4.封装结果集
            // 查找一对多，一个user可能有多个account，查到的每个account放在user的accountList里
            HashMap<Integer, User> resultMap = new HashMap<>();
            while (rs.next()) {
                int uid = rs.getInt("id");
                if (!resultMap.containsKey(uid)) {
                    User user = new User();
                    user.setId(uid);
                    resultMap.put(uid, user);
                }
                Account account = new Account();
                account.setId(rs.getInt("aid"));
                account.setUid(rs.getInt("id"));
                account.setMoney(rs.getFloat("money"));
                User user = resultMap.get(uid);
                user.setUsername(rs.getString("username"));
                if (user.getAccounts() == null) {
                    user.setAccounts(new ArrayList<>());
                }
                user.getAccounts().add(account);
            }

            for (Map.Entry<Integer, User> entry : resultMap.entrySet()) {
                User user = entry.getValue();
                System.out.println(user);
                System.out.println(user.getAccounts());
            }

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
