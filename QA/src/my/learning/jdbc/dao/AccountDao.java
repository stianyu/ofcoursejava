package my.learning.jdbc.dao;

import my.learning.jdbc.demo.Account;

import java.util.List;

public interface AccountDao {
    public Account findById(int id);

    public List<Account> findAll();

    public void updateMoneyById(int id, float money );
}
