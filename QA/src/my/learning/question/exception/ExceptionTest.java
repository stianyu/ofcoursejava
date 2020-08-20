package my.learning.question.exception;

public class ExceptionTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            System.out.println("t-----");
            int a = 1/0;
            System.out.println("------t");
        });

        t.start();
        System.out.println("m------");
        Thread.sleep(5000);
        System.out.println("-------m");
    }
}
