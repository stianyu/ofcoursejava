package my.answer.multithread.printabc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    static CountDownLatch countDownLatch1 = new CountDownLatch(1);
    static CountDownLatch countDownLatch2 = new CountDownLatch(1);

    public static void taskA() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": A");
            countDownLatch1.countDown();
        }
    }

    public static void taskB() {
        for (int i = 0; i < 10; i++) {
            try {
                countDownLatch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": B");
            countDownLatch2.countDown();
        }
    }

    public static void taskC() {
        for (int i = 0; i < 10; i++) {
            try {
                countDownLatch2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": C");
        }
    }

    public static void main(String[] args) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//        fixedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                CountDownLatchExample.taskA();
//            }
//        });
        fixedThreadPool.execute(CountDownLatchExample::taskA);
        fixedThreadPool.execute(CountDownLatchExample::taskB);
        fixedThreadPool.execute(CountDownLatchExample::taskC);

        fixedThreadPool.shutdown();
    }
}
