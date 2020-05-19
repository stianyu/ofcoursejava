package my.answer.multithread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 计数器线程与监听线程扩展解法5 BlokingQueue阻塞队列
 */
public class CountListener5 {

    // 阻塞队列，当队列为空时take出队方法会阻塞，直到队列里有元素放入
    static BlockingQueue<String> bq = new LinkedBlockingDeque<String>();

    static int count = 0;

    static void taskA() throws InterruptedException {
        System.out.println("A启动-开始计数");
        while (count < 10) {
            Thread.sleep(1000);
            count++;
            System.out.println("A计数值：" + count);
            if (count == 5 || count == 10) {// 计数值为5或10时，往阻塞队列放入商品
                bq.put("商品");
            }
        }
        System.out.println("A结束-结束计数");
    }

    static void taskB() throws InterruptedException {
        System.out.println("B启动-开始监听");
        bq.take();// 尝试从阻塞队列拿出商品
        System.out.println("B发出通知：计数值为" + count);// 计数值为5，发出通知
        bq.take();// 尝试从阻塞队列拿出商品
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
