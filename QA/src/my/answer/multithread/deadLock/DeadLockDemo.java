package my.answer.multithread.deadLock;

class HoldLockThread implements Runnable {
    private String lock1;
    private String lock2;

    public HoldLockThread(String lock1, String lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "\t持有" + lock1 + "\t尝试获取" + lock2);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "\t持有" + lock1 + "\t尝试获取" + lock2);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "Thread1").start();
        new Thread(new HoldLockThread(lockB, lockA), "Thread2").start();
    }
}
