package my.learning.jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 没有事务控制
 */
public class JDBCTransaction1 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils2.getConnection();
            String sql1 = "update account set money = money - 100 where id = 1 and money >= 100";
            String sql2 = "update account set money = money + 100 where id = 2";

            ps = conn.prepareStatement(sql1);
            int r1 = ps.executeUpdate();
            System.out.println("影响行数：" + r1);

            int a = 1/0;

            ps = conn.prepareStatement(sql2);
            int r2 = ps.executeUpdate();
            System.out.println("影响行数：" + r2);

            if (r1 > 0 && r2 > 0) {
                System.out.println("转账成功");
            } else {
                System.out.println("转账失败");
            }
        } catch (Exception e) {
            System.out.println("转账失败");
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JDBCUtils2.close(null, ps, conn);
        }
    }
}
