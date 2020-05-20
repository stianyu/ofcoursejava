package my.learning.jdbc.aop;

//日志通知对象
public class LoggerAdviser {

    @BeforeAspect
    public void beforeLog() {
        System.out.println("日志通知对象记录前置日志...");
    }

    @AfterAspect
    public void afterLog() {
        System.out.println("日志通知对象记录后置日志...");
    }

    @ExceptionAspect
    public void exceptionLog() {
        System.out.println("日志通知对象记录异常日志...");
    }

    @FinalAspect
    public void finalLog() {
        System.out.println("日志通知对象记录最终日志...");
    }

}
