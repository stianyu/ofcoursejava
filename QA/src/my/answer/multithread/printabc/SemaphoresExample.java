package my.answer.multithread.printabc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoresExample {

    static Semaphore semaphoreA = new Semaphore(1);
    static Semaphore semaphoreB = new Semaphore(0);
    static Semaphore semaphoreC = new Semaphore(0);

    public static void taskA() {
        for (int i = 0; i < 10; i++) {
            try {
                semaphoreA.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("A");
            semaphoreB.release();
        }
    }

    public static void taskB() {
        for (int i = 0; i < 10; i++) {
            try {
                semaphoreB.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("B");
            semaphoreC.release();
        }
    }

    public static void taskC() {
        for (int i = 0; i < 10; i++) {
            try {
                semaphoreC.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("C");
            semaphoreA.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        fixedThreadPool.execute(SemaphoresExample::taskA);
        fixedThreadPool.execute(SemaphoresExample::taskB);
        fixedThreadPool.execute(SemaphoresExample::taskC);

        fixedThreadPool.shutdown();

    }
}
