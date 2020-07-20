package my.pattern.singleton;

/**
 * 静态内部类
 *
 * 优点：同样使用了类加载机制创建了一个对象的实例，但它同时也和懒汉式一样，只有当需要创建这个类的适合才加载内部类进内存。
 *      因此综合了饿汉式加载的优点（线程安全）同时也具备懒汉式加载的优点（延迟加载）
 */
public class C2 {
    private static class C2Holder{
        public static C2 instance = new C2();
    }

    private C2() {

    }

    public static C2 newInstance() {
        return C2Holder.instance;
    }
}
