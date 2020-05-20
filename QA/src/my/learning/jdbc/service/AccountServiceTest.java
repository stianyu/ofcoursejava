package my.learning.jdbc.service;

import my.learning.jdbc.aop.AopFactory;
import my.learning.jdbc.aop.LoggerAdviser;
import my.learning.jdbc.transaction.TransactionFactory;
import org.junit.Test;

public class AccountServiceTest {
    private AccountService accountService = new AccountServiceImpl();
    private AccountService accountService2 = new AccountServiceImpl2();
    // 添加事务控制
    private AccountService accountService3 = (AccountService) TransactionFactory.enableTransaction(new AccountServiceImpl3());
    // 动态代理实现AOP日志记录
    private AccountService accountService4 = (AccountService) AopFactory.enableAop(new AccountServiceImpl3(), new LoggerAdviser());
    // 动态代理实现AOP事务控制
    private AccountService accountService5 = (AccountService) AopFactory.enableAop(new AccountServiceImpl3(), TransactionFactory.getTransactionManager());


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

    @Test
    public void testTransform4() {
        System.out.println(accountService4.transform(1, 2, 100));
    }

    @Test
    public void testTransform5() {
        System.out.println(accountService5.transform(1, 2, 100));
    }
}
