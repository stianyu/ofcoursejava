package my.pattern.singleton;

/**
 * DCL懒汉式单例模式
 */
class C2 {

    private static volatile C2 c;

    private C2() {

    }

    public static C2 getInstance() {
        if (c == null) {
            synchronized (C2.class) {
                if (c == null) {
                    c = new C2();
                }
            }

        }
        return c;
    }

}