package my.answer.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class MapTest {

	public static void main(String[] args) {
		System.out.println("-------------5.2)--------------");
		//5.2)
		Map<String, Integer> map = new HashMap<>();
		map.put("朱一", 1);
		map.put("朱二", 2);
		map.put("朱三", 3);
		map.put("朱四", 4);
		map.put("朱五", 5);
		map.put("朱六", 6);
		for(String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println();
		for(Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println();
		for(Map.Entry<String, Integer> e : map.entrySet()) {
			System.out.println(e.getKey() + " : " + e.getValue());
		}
		
		System.out.println("-------------5.3)--------------");
		//5.3)
		int[] arr = {1, 1, 2, 3, 3, 3, 4, 5};
		long start = System.nanoTime();
		System.out.println("使用TreeMap" + getArrayCount(arr));
		long end = System.nanoTime();
		System.out.println((end - start) / 1e9 * 1.0);
		start = System.nanoTime();
		System.out.println("使用HashMap" + getArrayCount(arr));
		end = System.nanoTime();
		System.out.println((end - start) / 1e9 * 1.0);
		
		System.out.println("-------------5.5)--------------");
		//5.5)
		Map<Student, Integer> mapStudent = new HashMap<>();
		mapStudent.put(new Student("a", 50), 1);
		mapStudent.put(new Student("a", 60), 1);
		mapStudent.put(new Student("a", 70), 2);
		mapStudent.put(new Student("b", 50), 2);
		mapStudent.put(new Student("c", 100), 3);
		mapStudent.put(new Student("d", 90), 4);
		for(Student keyStudent : mapStudent.keySet()) {
			System.out.println(keyStudent + " : " + mapStudent.get(keyStudent));
		}
		
		System.out.println("-------------5.6)--------------");
		//5.6)
		Map<Student2, Integer> mapStudent2 = new TreeMap<>();
		mapStudent2.put(new Student2("abc", 60), 1);
		mapStudent2.put(new Student2("acd", 60), 21);
		mapStudent2.put(new Student2("ade", 70), 1);
		mapStudent2.put(new Student2("b", 100), 2);
		mapStudent2.put(new Student2("c", 50), 3);
		mapStudent2.put(new Student2("d", 80), 4);
		for(Student2 keyStudent : mapStudent2.keySet()) {
			System.out.println(keyStudent + " : " + mapStudent2.get(keyStudent));
		}
		
	}
	
	//5.3) input : int[] arr = {1, 1, 2, 3, 3, 3, 4, 5}
	public static Map<Integer, Integer> getArrayCount1(int[] arr) {
		Map<Integer, Integer> map = new TreeMap<>();
		for(int i = 0; i < arr.length; i++) {
			if(map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1); 
			}
			else {
				map.put(arr[i], 1);
			}
		}
		return map;
	}
	
	//5.3) 使用TreeMap实现
	public static Map<Integer, Integer> getArrayCount(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}
		return map;
	}
	
}

//5.5)
class Student {
	private String name;
	private Integer score;
	
	public Student(String name, Integer score) {
		this.name = name;
		this.score = score;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o instanceof Student) {
			Student student = (Student) o;
			return Objects.equals(name, student.name) && Objects.equals(score, student.score);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, score);
	}
	
	@Override
	public String toString() {
		return "{" + name + " : " + score + "}";
	}
}

//5.6)
class Student2 implements Comparable<Student2>{
	private String name;
	private Integer score;
	
	public Student2(String name, Integer id) {
		this.name = name;
		this.score = id;
	}

	@Override
	public int compareTo(Student2 o) {
		if(score < o.score) {
			return 1;
		}
		else if(score > o.score) {
			return -1;
		}
		else {
			return name.compareTo(o.name);
		}
	}
	
	@Override
	public String toString() {
		return "{" + name + " : " + score + "}" ;
	}
}





















