package my.answer.collection;

import java.util.ArrayList;
import java.util.List;

public class ReverseTest {
	public static void reverse(List<Integer> list) {
		int length = list.size();
		for(int i = 0; i < length/2; i++) {
			Integer temp = list.get(i);
			list.set(i, list.get(length-1-i));
			list.set(length-1-i, temp);
		}		
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			list.add(i);
		}
		System.out.println(list);
		reverse(list);
		System.out.println(list);
	}

}
