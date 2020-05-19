package my.learning.jdbc.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCTransaction2 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils2.getConnection();
            conn.setAutoCommit(false);//1.开启事务
            String sql1 = "update account set money = money - 100 where id = 1 and money >= 100";
            String sql2 = "update account set money = money + 100 where id = 2";

            ps = conn.prepareStatement(sql1);
            int r1 = ps.executeUpdate();
            System.out.println("影响行数：" + r1);

//            int a = 1 / 0;

            ps = conn.prepareStatement(sql2);
            int r2 = ps.executeUpdate();
            System.out.println("影响行数：" + r2);
            conn.commit();//2.提交事务
            if (r1 > 0 && r2 > 0) {
                System.out.println("转账成功");
            } else {
                System.out.println("转账失败");
            }
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();//3.回滚事务
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("转账失败");
            e.printStackTrace();
        } finally {
            JDBCUtils2.close(null, ps, conn);//4.释放资源
        }
    }
}
