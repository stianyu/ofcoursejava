package my.answer.multithread;

public class ThreadSafeTest3 {

    private synchronized void fun() {
        int count = 10;
        while (count > 0) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
            System.out.println("[" + Thread.currentThread().getName() + "]当前count:" + count);
        }
    }

    public static void main(String[] args) {
        ThreadSafeTest3 obj = new ThreadSafeTest3();
        Thread t1 = new Thread(() -> obj.fun());
        Thread t2 = new Thread(() -> obj.fun());
        Thread t3 = new Thread(() -> obj.fun());
        Thread t4 = new Thread(() -> obj.fun());
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}