package my.learning.jdbc.service;


import my.learning.jdbc.dao.AccountDao;
import my.learning.jdbc.dao.AccountDaoImpl2;
import my.learning.jdbc.demo.Account;
import my.learning.jdbc.transaction.TransactionFactory;
import my.learning.jdbc.transaction.TransactionManager;

/**
 * 使用事务管理器,通过动态代理
 */
public class AccountServiceImpl3 implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl2();

    @Override
    public Boolean transform(int srcId, int talId, float money) {

        Account srcAccount = accountDao.findById(srcId);
        Account talAccount = accountDao.findById(talId);

        if (srcAccount.getMoney() < money) {
            return false;
        }
        accountDao.updateMoneyById(srcId, srcAccount.getMoney() - money);
        // 这里可能有异常
//        int i = 1 / 0;
        accountDao.updateMoneyById(talId, talAccount.getMoney() + money);

        return true;
    }

    @Override
    public Account findById(int id) {
        return accountDao.findById(id);
    }

}