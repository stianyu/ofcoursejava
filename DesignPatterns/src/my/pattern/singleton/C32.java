package my.pattern.singleton;

/**
 * 饿汉式单例模式
 *
 * 优点：在原来饿汉式的基础上，粗暴加锁避免了线程安全问题。
 * 缺点：因为直接对newInstance()方法直接加锁，当多个线程申请的时候，会因为得不到锁而阻塞，进行了无畏的锁资源竞争，程序效率不高
 */
public class C32 {
    private static C32 c3;

    private C32() {

    }

    public synchronized static C32 newInstance() {
        if (c3 == null) {
            c3 = new C32();
        }
        return c3;
    }
}
