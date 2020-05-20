package my.learning.jdbc.service;

import my.learning.jdbc.transaction.TransactionFactory;
import org.junit.Test;

public class AccountServiceTest {
    private AccountService accountService = new AccountServiceImpl();
    private AccountService accountService2 = new AccountServiceImpl2();
    private AccountService accountService3 = (AccountService) TransactionFactory.enableTransaction(new AccountServiceImpl3());


    @Test
    public void testTransform() {
        System.out.println(accountService.transform(1, 2, 100));
    }

    @Test
    public void testFindId() {
        System.out.println(accountService.findById(1));
    }

    @Test
    public void testTransform2() {
        System.out.println(accountService2.transform(1, 2, 100));
    }

    @Test
    public void testTransform3() {
        System.out.println(accountService3.transform(1, 2, 100));
    }
}
