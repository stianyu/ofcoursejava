package my.learning.jdbc.dao;

import my.learning.jdbc.demo.Account;
import my.learning.jdbc.demo.BeanUtils;
import my.learning.jdbc.demo.JDBCUtils2;
import my.learning.jdbc.transaction.ConnectionUtils;
import my.learning.jdbc.transaction.TransactionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDaoImpl2 implements AccountDao{

    private ConnectionUtils connectionUtils = TransactionFactory.getConnectionUtils();

    @Override
    public Account findById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //1.注册驱动(已解决)
            //2.获取连接（已解决）
            conn = connectionUtils.getThreadConnection();
            String sql = "select * from account where id = ?";

            //3.执行sql
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 2);
            rs = ps.executeQuery();

            //4.封装结果集 (已解决)
            Account account = BeanUtils.populate(rs, Account.class);
            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils2.close(rs, ps, null);
        }
        return null;
    }

    @Override
    public List<Account> findAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //1.注册驱动(已解决)
            //2.获取连接（已解决）
            conn = connectionUtils.getThreadConnection();
            String sql = "select * from account";

            //3.执行sql
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            //4.封装结果集 (已解决)
            List<Account> accounts = BeanUtils.populateList(rs, Account.class);
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5.释放资源 4.写法繁琐（已解决），频繁释放资源（已解决）
            JDBCUtils2.close(rs, ps, null);
        }
        return null;
    }

    @Override
    public void updateMoneyById(int id, float money) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //1.注册驱动(已解决)
            //2.获取连接（已解决）
            conn = connectionUtils.getThreadConnection();
            String sql = "update account set money = ? where id = ?";

            //3.执行sql
            ps = conn.prepareStatement(sql);
            ps.setFloat(1, money);
            ps.setInt(2, id);
            ps.execute();

            //4.封装结果集 (已解决)
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils2.close(ps, null);
        }
    }

}
