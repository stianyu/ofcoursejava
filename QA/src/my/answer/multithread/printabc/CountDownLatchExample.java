package my.answer.multithread.printabc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    static CountDownLatch countDownLatch1 = new CountDownLatch(1);
    static CountDownLatch countDownLatch2 = new CountDownLatch(1);

    public static void taskA() {
        for (int i = 0; i < 10; i++) {
            System.out.println("A");
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
            System.out.println("B");
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
        }
    }

    public static void main(String[] args) {
        CountDownLatchExample countDownLatchExample = new CountDownLatchExample();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        fixedThreadPool.submit(CountDownLatchExample::taskA);
        fixedThreadPool.submit(CountDownLatchExample::taskB);
        fixedThreadPool.submit(() -> {
            taskC();
        });

        fixedThreadPool.shutdown();
    }
}
