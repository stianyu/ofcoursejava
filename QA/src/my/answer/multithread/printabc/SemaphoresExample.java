package my.answer.multithread.printabc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoresExample {

    private Semaphore semaphoreA = new Semaphore(1);
    private Semaphore semaphoreB = new Semaphore(0);
    private Semaphore semaphoreC = new Semaphore(0);

    public static void main(String[] args) {
        SemaphoresExample semaphoresExample = new SemaphoresExample();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        fixedThreadPool.submit(semaphoresExample.new RunnableA());
        fixedThreadPool.submit(semaphoresExample.new RunnableB());
        fixedThreadPool.submit(semaphoresExample.new RunnableC());

        fixedThreadPool.shutdown();

    }

    private class RunnableA implements Runnable {

        @Override
        public void run() {
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
    }

    private class RunnableB implements Runnable {
        @Override
        public void run() {
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
    }

    private class RunnableC implements Runnable {
        @Override
        public void run() {
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
    }
}
