package my.answer.multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 计数器线程与监听线程扩展解法4 使用障碍工具CyclicBarrier
 */
public class CountListener4 {

    static CyclicBarrier barrier = new CyclicBarrier(2);// 设置两个线程就位时跨越障碍

    static int count = 0;

    static void taskA() throws InterruptedException, BrokenBarrierException {
        System.out.println("A启动-开始计数");
        while (count < 10) {
            Thread.sleep(1000);
            count++;
            System.out.println("A计数值：" + count);
            if (count == 5 || count == 10) {// 计数值为5或10，线程A就位等待跨越障碍
                barrier.await();
            }
        }
        System.out.println("A结束-结束计数");
    }

    static void taskB() throws InterruptedException, BrokenBarrierException {
        System.out.println("B启动-开始监听");
        barrier.await();// 线程B一启动就就位，等待A就位后跨越障碍
        barrier.reset();// 重置障碍
        System.out.println("B发出通知：计数值为" + count);// 计数值为5，发出通知
        barrier.await();// 线程B再次等待A就位后跨越障碍
        System.out.println("B结束-结束监听");
    }

    public static void main(String[] args) {
        Thread tB = new Thread(() -> {
            try {
                taskB();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        tB.start();// B先启动
        Thread tA = new Thread(() -> {
            try {
                taskA();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        tA.start();
    }

}
