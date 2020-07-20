package my.pattern.singleton;

/**
 * 饿汉式单例模式
 *
 * 优点：某个单例使用的次数少，在需要用的时候创建，节约了类加载之后实例占据的内存空间。
 * 缺点：不是线程安全的，需要加synchronized关键字或者采取更高效的加锁方式实现，比如双重校验锁。
 */
public class C3 {
    private static C3 c3;

    private C3() {

    }

    public static C3 newInstance() {
        if (c3 == null) {
            c3 = new C3();
        }
        return c3;
    }
}
