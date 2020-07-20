package my.pattern.singleton;

/**
 * 饿汉式单例模式
 *
 * 好处：在类加载时一次创建，不会存在多线程创建多个实例的情况出现，避免了多线程安全问题。
 * 坏处是：如果这个类需要使用的次数极少，那么类一加载的时候就创建了实例，就浪费了内存空间。
 */
public class C {
	
	private static final C c = new C();

	private C() {
		
	}

	public static C getInstance() {
			return c;
	}
	
}
