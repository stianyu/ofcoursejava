package my.learning.jdbc.service;


import my.learning.jdbc.dao.AccountDao;
import my.learning.jdbc.dao.AccountDaoImpl2;
import my.learning.jdbc.demo.Account;
import my.learning.jdbc.transaction.TransactionFactory;
import my.learning.jdbc.transaction.TransactionManager;

/**
 * 使用事务管理器
 */
public class AccountServiceImpl2 implements AccountService {
    private AccountDao accountDao = new AccountDaoImpl2();
    private TransactionManager transactionManager = TransactionFactory.getTransactionManager();

    @Override
    public Boolean transform(int srcId, int talId, float money) {
        try {
            // 开启事务（设计一个事务管理器，给业务层调用）
            transactionManager.beginTransaction();
            Account srcAccount = accountDao.findById(srcId);
            Account talAccount = accountDao.findById(talId);
            if (srcAccount.getMoney() < money) {
                return false;
            }
            accountDao.updateMoneyById(srcId, srcAccount.getMoney() - money);
            // 这里可能有异常
            int i = 1 / 0;
            accountDao.updateMoneyById(talId, talAccount.getMoney() + money);
            // 提交事务
            transactionManager.commit();
        } catch (Exception e) {
            // 回滚事务
            transactionManager.rollback();
            e.printStackTrace();
        } finally {
            // 释放资源
            transactionManager.release();
        }
        return true;
    }

    @Override
    public Account findById(int id) {
        return accountDao.findById(id);
    }
}
