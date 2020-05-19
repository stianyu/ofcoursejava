package my.learning.generic;

import java.util.Arrays;

/**
 * 普通容器类
 *
 * 知识点1: 在类上定义泛型，在类中使用泛型
 */
class Container<E> {
	private E e;

	public Container() {
	}

	public Container(E e) {
		this.e = e;
	}

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

}

/**
 * 数字容器类
 *
 * <pre>
 * 知识点2：泛型可以继承(Container<E>)，也可以不继承(Comparable<NumberContainer<?>>)
 * 知识点3：使用限制符（extends或super）限制泛型的范围
 * 知识点4：使用通配符?接收或推断限制范围内的任意泛型（?表示指定泛型而不是定义泛型）
 * </pre>
 */
class NumberContainer<E extends Number> extends Container<E> implements Comparable<NumberContainer<?>> {

	public NumberContainer() {
	}

	public NumberContainer(E e) {
		super(e);
	}

	@Override
	public String toString() {
		return "Number" + super.toString();// 继续用父类的toString，前面拼个Number
	}

	@Override
	public boolean equals(Object obj) {// equals的标准写法
		if (this == obj) { // 1.比较引用地址
			return true;
		}
		if (obj instanceof NumberContainer) { // 2.判断类型
			NumberContainer<?> o = (NumberContainer<?>) obj;// 3.向下转型, 可以和任意泛型的NumberContainer比较
			if (this.getElement() == null) { // 4.实际比较逻辑
				return o.getElement() == null;
			} else {
				double val1 = this.getElement().doubleValue();// 将元素值都转换为精度最高的double型
				double val2 = o.getElement().doubleValue();
				return val1 == val2; // 比较元素值是否相等
			}
		}
		return false;
	}

	@Override // 空元素不能比较，调用这个方法会出NullPointerException
	public int compareTo(NumberContainer<?> o) { // 用?可以和任意泛型的NumberContainer比较
		double val1 = this.getElement().doubleValue();
		double val2 = o.getElement().doubleValue(); // 将元素值都转换为精度最高的double型
		if (val1 < val2) { // 按照compareTo的定义写比较逻辑
			return -1;
		} else if (val1 > val2) {
			return 1;
		} else {
			return 0;
		}
	}
}

/**
 * 数字容器的工具类
 *
 * <pre>
 * 知识点5：在方法上定义泛型，在方法的参数/返回值和方法体中使用泛型
 * 知识点6：可定义泛型数组和带泛型的数组
 * </pre>
 */
class NumberContainerUtils {
	// 交换容器A和B中的元素
	public static <E extends Number> void swap(NumberContainer<E> containerA, NumberContainer<E> containerB) {
		E temp = containerA.getElement();
		containerA.setElement(containerB.getElement());
		containerB.setElement(temp);
	}

	// 从小到大排序数字容器数组array
	public static <E extends Number> void sort(NumberContainer<E>[] array) {
		int N = array.length;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (array[i].compareTo(array[j]) > 0) {// 使用compareTo比较
					NumberContainer<E> temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}

	}

	// 选择排序法
	public static void sort(int[] arr) {
		int N = arr.length;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

}

/**
 * 代码测试
 *
 * 知识点7：用真实类型或通配符指定泛型
 */
public class Result {

	/**
	 * <pre>
	 * 细节补充：
	 * 1）创建对象时如果左边类名处写了泛型，右边类名处只需写菱形运算符<>（创建带泛型数组时不适用）。
	 * 2）创建对象时如果想指定泛型为通配符，可以不写，编译器会发出警告但不影响运行。
	 * 3）泛型不支持上下转型，如 List<Object> list = new ArrayList<String>()会编译失败。想要实现类似转型的效果可以使用通配符。
	 * 4）如果想让一个方法接收带不同泛型的参数类型，除了使用通配符（本例CompareTo方法），也可以在方法上定义泛型（本例swap方法）。
	 * </pre>
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("------------普通容器--------------");
		Container<String> c = new Container<>("abc");
		System.out.println(c.getElement());
		c.setElement("bcd");
		System.out.println(c);

		System.out.println("\n---------数字容器及其equals比较相等方法---------");
		// NumberContainer<Number> nc1 = new NumberContainer<>("123");//编译失败
		NumberContainer<Double> nc1 = new NumberContainer<>(123.0);
		NumberContainer<Integer> nc2 = new NumberContainer<>(123);
		System.out.println(nc1);
		System.out.println(nc2);
		System.out.println("两个不同泛型的数字容器相等？" + nc1.equals(nc2));

		System.out.println("\n-------数字容器的comparaTo比大小方法-------");
		NumberContainer<Integer> nc3 = new NumberContainer<>(125);
		NumberContainer<Double> nc4 = new NumberContainer<>(125.1);
		System.out.println(nc3);
		System.out.println(nc4);
		System.out.println("两个不同泛型的数字容器比大小：" + nc3.compareTo(nc4));

		System.out.println("\n---------工具类的交换元素swap方法----------");
		NumberContainer<Integer> nc5 = new NumberContainer<>(13);
		NumberContainer<Integer> nc6 = new NumberContainer<>(99);
		NumberContainerUtils.swap(nc5, nc6);
		System.out.println(nc5);
		System.out.println(nc6);

		System.out.println("\n------------工具类的排序sort方法----------");
//		int[] arr = { 5, 2, 4, 3, 1 };
//		NumberContainerUtils.sort(arr);//测试选择排序法
//		System.out.println(Arrays.toString(arr));

		NumberContainer<?>[] array = new NumberContainer<?>[6];// ?可以不写，只是会有警告
		array[0] = nc1;
		array[1] = nc2;
		array[2] = nc3;
		array[3] = nc4;
		array[4] = nc5;
		array[5] = nc6;
//		NumberContainerUtils.sort(array);
		System.out.println(Arrays.toString(array));
	}
}
