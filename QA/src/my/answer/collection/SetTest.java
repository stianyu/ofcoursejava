package my.answer.collection;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
	
	//获得集合第一个元素
	public static Integer getSet(Set<Integer> set) {
		return set.iterator().next();
	}

	public static void main(String[] args) {
		
		//3.3)
		Set<Double> set1 = new HashSet<>();
		Set<Double> set2 = new TreeSet<>();
		Random random = new Random();
		for(int i = 0; i < 10; i++) {
			Double data = random.nextDouble();
			set1.add(data);
			set2.add(data);
		}
		Iterator<Double> it1 = set1.iterator();
		Iterator<Double> it2 = set2.iterator();
		while(it1.hasNext()) {
			System.out.println("HashSet实现Set的值： " + it1.next() + ", TreeSet实现Set的值" + it2.next());
		}

		//3.4)
		Set<Integer> set3 = new HashSet<>();
		set3.add(20);
		System.out.println(getSet(set3));
		
		System.out.println("-------------3.7) -- Comparator实现-----------");
		//3.7) -- Comparator实现
		Set<Integer> set4 = new TreeSet<>(
				new Comparator<Integer>() {
					@Override
					public int compare(Integer a, Integer b) {
						return b - a;
					}
				});
		for(int i = 0; i < 10; i++) {
			set4.add(random.nextInt(10));
		}
		for(Integer e : set4) {
			System.out.println(e);
		}
		
		System.out.println("-------------3.7) -- Comparable实现-----------");
		
		//3.7) -- Comparable实现
		Set<Integer1> set5 = new TreeSet<>();
		for(int i = 0; i < 10; i++) {
			set5.add(new Integer1(random.nextInt(10)));
		}
		for(Integer1 e : set5) {
			System.out.println(e);
		}
	}
}

//3.7)
class Integer1 implements Comparable<Integer1>{
	Integer e;
	
	public Integer1(Integer e) {
		this.e = e;
	}

	@Override
	public int compareTo(Integer1 o) {
		return o.e - e;
	}
	
	@Override
	public String toString() {
		return e.toString();
	}
}






