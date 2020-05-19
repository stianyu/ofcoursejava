package my.learning.jdbc.service;

import org.junit.Test;

public class AccountServiceTest {
    private AccountService accountService = new AccountServiceImpl();
    private AccountService accountService2 = new AccountServiceImpl2();

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
}
