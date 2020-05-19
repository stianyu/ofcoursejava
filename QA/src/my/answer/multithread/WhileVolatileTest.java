package my.answer.multithread;

public class WhileVolatileTest {
    private static volatile boolean flag = false;
    public static void main(String[] args) {
        Thread A = new Thread() {
            @Override
            public void run() {
                System.out.println("A启动-开始计时");
                int seconds = 0;
                while(seconds < 10) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seconds++;
                    if(seconds != 5) {
                        System.out.println("A计数器：" +  seconds);
                    }
                    if(seconds == 5){
                        flag = true;
                    }
                    if(seconds == 10) {
                        System.out.println("A结束-结束计数");
                    }
                }
            }
        };
        Thread B = new Thread() {
            @Override
            public void run() {
                System.out.println("B启动-开始监听");
                while(flag) {
                    System.out.println("B发出通知：计数值为5");
                }
                System.out.println("B结束-结束监听");
            }
        };
        B.start();
        A.start();
    }
}
