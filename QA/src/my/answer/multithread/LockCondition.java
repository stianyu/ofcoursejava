package my.answer.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCondition {
    private static int seconds = 0;
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition timesUp = lock.newCondition();
        Thread A = new Thread() {
            @Override
            public void run() {
                System.out.println("A启动-A开始计数");
                lock.lock();
                try {
                    while(seconds < 10) {
                        Thread.sleep(100);
                        seconds++;
                        if(seconds != 5){
                            System.out.println("A计数器：" + seconds);
                        }
                        if(seconds == 5) {
                            timesUp.signalAll();
                            timesUp.await();
                        }
                        if(seconds == 10) {
                            System.out.println("A结束-结束计数");
                            timesUp.signalAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        Thread B = new Thread() {
            @Override
            public void run() {
                System.out.println("B启动-开始监听");
                lock.lock();
                try {
                    if(seconds != 5) {
                        timesUp.await();
                    }
                    if(seconds == 5){
                        System.out.println("B发出通知：计数值为5");
                        timesUp.signalAll();
                        timesUp.await();
                    }
                    if(seconds == 10) {
                        System.out.println("B结束-结束监听");
                    }
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        B.start();
        A.start();
    }
}
