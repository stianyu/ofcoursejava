package my.learning.jdbc.service;


import my.learning.jdbc.demo.Account;

public interface AccountService {
    public Boolean transform(int srcId, int talId, float money);

    public Account findById(int id);
}
