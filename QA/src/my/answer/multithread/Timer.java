package my.answer.multithread;

public class Timer {
    private static int seconds = 0;
    public static void main(String[] args) {
        Object obj = new Object();
        Thread A = new Thread() {
            @Override
            public void run() {
                System.out.println("A启动-A开始计数");
                synchronized (obj) {
                    while(seconds < 10) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        seconds++;
                        if(seconds != 5){
                            System.out.println("A计数器：" +  seconds);
                        }
                        if(seconds == 5) {
                            obj.notifyAll();
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(seconds == 10) {
                            System.out.println("A结束-结束计数");
                            obj.notifyAll();
                            //这里不注释停止不了
//                            try {
//                                obj.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
                        }
                    }
                }
            }
        };

        Thread B = new Thread() {
            @Override
            public void run() {
                System.out.println("B启动-开始监听");
                synchronized (obj) {
                    if(seconds != 5) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if(seconds == 5){
                        System.out.println("B发出通知：计数值为5");
                        obj.notifyAll();
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("B结束-结束监听");
                }
            }
        };
        B.start();
        A.start();
    }
}