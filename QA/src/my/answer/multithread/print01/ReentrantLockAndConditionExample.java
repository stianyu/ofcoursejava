package my.answer.multithread.print01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockAndConditionExample {
    private static int count = 0;

    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition condition1 = reentrantLock.newCondition();
    private static Condition condition2 = reentrantLock.newCondition();

    public static void taskA() {
        while(count <= 100) {
            reentrantLock.lock();
            System.out.println("偶数" + count++);
            try {
                condition1.await();   // 把偶数线程阻塞
                condition2.signal();  // 把奇数线程唤醒
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void taskB() {
        while(count <= 100) {
            reentrantLock.lock();
            System.out.println("奇数" + count++);
            try {
                condition1.signal();  // 把偶数线程唤醒
                condition2.await();   // 把奇数线程阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        fixedThreadPool.execute(ReentrantLockAndConditionExample::taskA);
        fixedThreadPool.execute(ReentrantLockAndConditionExample::taskB);

        fixedThreadPool.shutdown();
    }
}
