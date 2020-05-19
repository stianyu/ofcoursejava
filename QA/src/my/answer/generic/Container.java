package my.answer.generic;

class A {
	String name = "sty";
	
	public A(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "myname is " + name;
	}
}

public class Container<E> {
	private E e;
	
	public E getElement() {
		return e;
	}
	
	public void setElement(E e) {
		this.e = e;
	}
	
	@Override
	public String toString() {
		return "Container{" + e + "}";
	}
	
	public static void main(String[] args) {
		Container<Integer> a = new Container<>();
		a.setElement(1);
		System.out.println(a.getElement());
		System.out.println(a);

	}

}
