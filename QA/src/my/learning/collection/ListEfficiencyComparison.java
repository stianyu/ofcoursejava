package my.learning.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListEfficiencyComparison {
	//尾部添加
	public static double testTime1(List<Integer> list) {
		long start = System.nanoTime();
		for(int i = 0; i < 200000; i++) {
			list.add(i);
		}
		long end = System.nanoTime();

		return (end - start)/1e9 * 1.0;
	}

	//头部添加
	public static double testTime2(List<Integer> list) {
		long start = System.nanoTime();
		for(int i = 0; i < 200000; i++) {
			list.add(0, i);
		}
		long end = System.nanoTime();
		return (end-start)/1e9*1.0;
	}

	//get方法
	public static double testTime3(List<Integer> list) {
		long start = System.nanoTime();
		for(int i = 0; i < 200000; i++) {
			list.get(i);
		}
		long end = System.nanoTime();
		return (end-start)/1e9*1.0;
	}

	//set方法
	public static double testTime4(List<Integer> list) {
		long start = System.nanoTime();
		for(int i = 0; i < 200000; i++) {
			list.set(i, i+1);
		}
		long end = System.nanoTime();
		return (end-start)/1e9*1.0;
	}

	//头部删除
	public static double testTime5(List<Integer> list) {
		long start = System.nanoTime();
		for(int i = 0; i < 200000; i++) {
			list.remove(0);
		}
		long end = System.nanoTime();
		return (end-start)/1e9*1.0;
	}

	public static double testTime8(List<Integer> list) {
		long start = System.nanoTime();
		LinkedList<Integer> list2 = (LinkedList<Integer>) list;
		while(list.size() != 0) {
			list2.removeFirst();
		}
		long end = System.nanoTime();
		return (end-start)/1e9*1.0;
	}

	//ArrayList尾部删除
	public static double testTime6(List<Integer> list) {
		long start = System.nanoTime();
		while(list.size() != 0) {
			list.remove(list.size()-1);
		}
		long end = System.nanoTime();
		return (end-start)/1e9*1.0;
	}

	//LinkedList尾部删除
	public static double testTime7(List<Integer> list) {
		long start = System.nanoTime();
		LinkedList<Integer> list2 = (LinkedList<Integer>) list;
		while(list.size() != 0) {
			list2.removeLast();
		}
		long end = System.nanoTime();
		return (end-start)/1e9*1.0;
	}

	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new LinkedList<>();
		System.out.println("ArrayList在尾部添加元素耗时：" + testTime1(list1) + " s");
		System.out.println("LinkedList在尾部添加元素耗时:" + testTime1(list2) + " s");
//		list1.clear();
//		list2.clear();
//		System.out.println("ArrayList在头部添加元素耗时：" + testTime2(list1) + " s");
//		System.out.println("LinkedList在头部添加元素耗时:" + testTime2(list2) + " s");
//		System.out.println("ArrayList的get方法遍历一遍元素耗时：" + testTime3(list1) + " s");
//		System.out.println("LinkedList的get方法遍历一遍元素耗时:" + testTime3(list2) + " s");
//		System.out.println("ArrayList的set方法遍历一遍元素耗时：" + testTime4(list1) + " s");
//		System.out.println("LinkedList的set方法遍历一遍元素耗时:" + testTime4(list2) + " s");
		System.out.println("ArrayList在头部删除元素耗时：" + testTime5(list1) + " s");
		System.out.println("LinkedList在头部删除元素耗时:" + testTime5(list2) + " s");
		System.out.println("ArrayList在尾部删除元素耗时：" + testTime6(list1) + " s");
		System.out.println("LinkedList在尾部删除元素耗时:" + testTime6(list2) + " s");
	}

}
