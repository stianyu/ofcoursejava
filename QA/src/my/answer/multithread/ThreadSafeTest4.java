package my.answer.multithread;

public class ThreadSafeTest4 {

    private static int count = 10;

    private synchronized static void fun() {
        while (count > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
            System.out.println("[" + Thread.currentThread().getName() + "]当前count:" + count);
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> fun());
        Thread t2 = new Thread(() -> fun());
        Thread t3 = new Thread(() -> fun());
        Thread t4 = new Thread(() -> fun());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}