package my.learning.jdbc.dao;


import my.learning.jdbc.demo.Account;
import org.junit.Test;

import java.util.List;

public class AccountDaoTest {
    private AccountDao accountDao = new AccountDaoImpl();

    @Test
    public void testFindById() {
        Account account = accountDao.findById(1);
        System.out.println(account);
    }

    @Test
    public void testFindAll() {
        List<Account> accounts = accountDao.findAll();
        System.out.println(accounts);
    }

    @Test
    public void testUpdateMoneyById() {
        accountDao.updateMoneyById(1, 20);
    }
}
