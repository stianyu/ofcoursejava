package my.pattern.singleton;

public class test {

	public static void main(String[] args) {
		C c2,c3,c4;
		String string1 = "abc";
		String string = new String("abc");
		String string2 = new String();
		System.out.println(string.hashCode()+ " / " + string1.hashCode() + " / " + string2.hashCode());
		c2 = C.getInstance();
		c3 = C.getInstance();
		c4 = C.getInstance();
//		C.c=null;
//		System.out.println(C.c.hashCode());
		System.out.println(c2 == c3);
		System.out.println(c3.hashCode());
		System.out.println(c4.hashCode());

		C33 c5 = C33.getInstance();
		C33 c6 = C33.getInstance();
		System.out.println(c5 == c6);
		System.out.println(c5.hashCode());
		System.out.println(c6.hashCode());

	}

}
