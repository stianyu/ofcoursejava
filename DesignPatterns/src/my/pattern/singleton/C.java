package my.pattern.singleton;

/**
 * 饿汉式单例模式
 */
public class C {
	
	private static final C c = new C();

	private C() {
		
	}

	public static C getInstance() {
			return c;
	}
	
}
