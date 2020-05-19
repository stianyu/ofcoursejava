package my.answer.generic;

import java.util.Arrays;
import java.util.Random;

public class NumberContainerUtils {
	
	public static <E extends Number> void swap(NumberContainer<E> containerA, NumberContainer<E> containerB) {
		E temp =  containerA.getElement();
		containerA.setElement(containerB.getElement());
		containerB.setElement(temp);	
	}
	
	public static <E extends Number> void sort(NumberContainer<E>[] containerArray) {
		for(int i = 0; i < containerArray.length; i++) {
			for(int j = 0; j < containerArray.length; j++) {
				if(containerArray[i].compareTo(containerArray[j]) < 0) {
					swap(containerArray[i], containerArray[j]);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		NumberContainer<Float> a1 = new NumberContainer<>();
		a1.setElement(new Float(3.2));
		NumberContainer<Float> a2 = new NumberContainer<>();
		a2.setElement(new Float(2.6));
		swap(a1, a2);
		System.out.println(a1);
		System.out.println(a2);
		
		NumberContainer<?>[] containerArray = new NumberContainer<?>[13];
		Random random = new Random();
		for(int i = 0; i < containerArray.length - 3; i++) {
			NumberContainer<Integer> temp = new NumberContainer<>();
			temp.setElement(random.nextInt(50));
			containerArray[i] = temp;

//			containerArray[i] = new NumberContainer<Integer>();
//			containerArray[i].setElement(random.nextInt(50));
		}
		containerArray[10] = a1;
		containerArray[11] = a2;
		NumberContainer<Double> a3 = new NumberContainer<>();
		a3.setElement(new Double(3.5));
		containerArray[12] = a3;
//		System.out.println(toString(containerArray));
		System.out.println(Arrays.toString(containerArray));
//		sort(containerArray);
//		System.out.println(toString(containerArray));
		System.out.println(Arrays.toString(containerArray));
	}

	public static String toString(NumberContainer<?>[] containerArray) {
		StringBuilder string = new StringBuilder();
		string.append("[");
		for(int i = 0; i < containerArray.length; i++) {
			string.append(containerArray[i]);
			if(i != containerArray.length - 1) {
				string.append(", ");
			}
		}
		string.append("]");
		return string.toString();
	}
	
}
