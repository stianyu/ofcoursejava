package my.pattern.singleton;

/**
 * DCL懒汉式单例模式
 *
 * 优点：1.延迟加载； 2.线程安全； 3.执行效率高
 * 使用了 volatile 关键字修饰 c，在这里的作用体现为：1.保证了变量可见性； 2.禁止指令重排（第二点体现在对象的创建过程中）
 */
class C33 {

    private static volatile C33 c;

    private C33() {

    }

    public static C33 getInstance() {
        if (c == null) {
            synchronized (C33.class) {
                if (c == null) {
                    c = new C33();
                }
            }

        }
        return c;
    }

}