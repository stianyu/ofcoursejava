package my.answer.multithread;

import java.util.concurrent.CountDownLatch;

/**
 * 计数器线程与监听线程扩展解法3 使用倒计时工具CountDownLatch
 */
public class CountListener3 {

    static CountDownLatch latch5 = new CountDownLatch(1);
    static CountDownLatch latch10 = new CountDownLatch(1);

    static int count = 0;

    static void taskA() throws InterruptedException {
        System.out.println("A启动-开始计数");
        while (count < 10) {
            Thread.sleep(1000);
            count++;
            System.out.println("A计数值：" + count);
            if (count == 5) {//计数值为5，释放latch5
                latch5.countDown();
            }
            if (count == 10) {//计数值为10，释放latch10
                latch10.countDown();
            }
        }
        System.out.println("A结束-结束计数");
    }

    static void taskB() throws InterruptedException {
        System.out.println("B启动-开始监听");
        latch5.await();// 等待latch5的计数变为0
        System.out.println("B发出通知：计数值为" + count);// 计数值为5，发出通知
        latch10.await();// 等待latch10的计数变为0
        System.out.println("B结束-结束监听");
    }

    public static void main(String[] args) {
        Thread tB = new Thread(() -> {
            try {
                taskB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        tB.start();// B先启动
        Thread tA = new Thread(() -> {
            try {
                taskA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        tA.start();
    }

}
