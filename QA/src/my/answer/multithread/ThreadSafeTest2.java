package my.answer.multithread;

public class ThreadSafeTest2 implements Runnable {

    private int count = 100;

    Object obj = new Object();

    @Override
    public void run() {
        synchronized (obj) {
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
    }

    public static void main(String[] args) {
        Runnable r = new ThreadSafeTest2();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);
        Thread t4 = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}