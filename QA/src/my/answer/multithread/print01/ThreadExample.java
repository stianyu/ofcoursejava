package my.answer.multithread.print01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExample {

    private volatile static int count = 0;
    private volatile static boolean flag = true;
    public static void main(String[] args) {
        // 启用线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 先从0，偶数开始
        executorService.execute(() -> {
            while (count <= 100) {
                if (flag) {
                    System.out.println("偶数：" + count++);
                    flag = false;
                }
            }
        });
        executorService.execute(() -> {
            while (count <= 100) {
                if (!flag) {
                    System.out.println("奇数：" + count++);
                    flag = true;
                }
            }
        });
        executorService.shutdown();
    }
}
