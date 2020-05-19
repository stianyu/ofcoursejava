package my.answer.collection;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class QueueTest {

	//4.3)
	public static String rotate(String skr, int k) {
		Queue<Character> queue = new LinkedList<>();
		for(int i = 0; i < skr.length(); i++) {
			queue.add(skr.charAt(i));
		}
		for(int i = 0; i < k; i++) {
			queue.add(queue.poll());
		}
		StringBuilder string = new StringBuilder();
		for(Character e : queue) {
			string.append(e);
		}
		
		return string.toString();
		
	}
	
	//4.7)
	public static void search(Set<Integer> set) {
		Queue<Integer> queue = new ArrayDeque<>();
		
	}
	
	public static void main(String[] args) {
		//4.3)test
		System.out.println(rotate("abcde", 3));
		
		//4.7)test
		Set<Integer> set = new TreeSet<>();
		Random random = new Random();
		for(int i = 0; i < 10; i++) {
			set.add(random.nextInt(20));
		}
		search(set);
		
	}

}










