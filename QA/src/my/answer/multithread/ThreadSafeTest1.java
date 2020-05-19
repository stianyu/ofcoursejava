package my.answer.multithread;

public class ThreadSafeTest1 extends Thread {

    private int count = 100;

    @Override
    public void run() {
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
        Thread t1 = new ThreadSafeTest1();
        Thread t2 = new ThreadSafeTest1();
        Thread t3 = new ThreadSafeTest1();
        Thread t4 = new ThreadSafeTest1();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}