package my.learning.collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CollectionTimeTest {

	enum ListTestType {// List的测试项目
		ADD_FIRST, ADD_LAST, ADD, GET, SET, REMOVE_FIRST, REMOVE, REMOVE_LAST, CONTAIN
	}

	enum SetTestType {// Set的测试项目
		ADD, CONTAIN, REMOVE
	}

	public static int[] generateRandomArray(int n, int bound) {// 生成长度为n的随机数组
		Random r = new Random();
		int[] arr = new int[n];
		for (int j = 0; j < n; j++) {
			arr[j] = r.nextInt(bound);
		}
		return arr;
	}

	// 测试List集合通用方法
	public static void testList(String testname, ListTestType type, List<Integer> list, int[] arr) {
		long startTime = System.nanoTime();
		for (int i = 0; i < arr.length; i++) {
			switch (type) {
			case ADD_FIRST:
				list.add(0, arr[i]);
				break;
			case ADD_LAST:
			case ADD:
				list.add(arr[i]);
				break;
			case GET:
				list.get(i);
				break;
			case SET:
				list.set(i, arr[i]);
				break;
			case CONTAIN:
				list.contains(arr[i]);
				break;
			case REMOVE_FIRST:
				list.remove(0);
				break;
			case REMOVE_LAST:
				list.remove(list.size() - 1);
				break;
			case REMOVE:
				Object obj = arr[i];
				list.remove(obj);
				break;
			default:
				break;
			}
		}
		long endTime = System.nanoTime();
		System.out.println(testname + " : " + (endTime - startTime) / 1e9 + "s");
	}

	// 测试Set集合通用方法
	public static void testSet(String testname, SetTestType type, Set<Integer> set, int[] arr) {
		long startTime = System.nanoTime();
		for (int i = 0; i < arr.length; i++) {
			switch (type) {
			case ADD:
				set.add(arr[i]);
				break;
			case CONTAIN:
				set.contains(arr[i]);
				break;
			case REMOVE:
				set.remove(arr[i]);
				break;
			default:
				break;
			}
		}
		long endTime = System.nanoTime();
		System.out.println(testname + " : " + (endTime - startTime) / 1e9 + "s");
	}

	// -----------------------------------使用JUnit单元测试-------------------------------------------------------------//
	// 要测试的集合类
	TreeSet<Integer> treeSet;
	HashSet<Integer> hashSet;
	ArrayList<Integer> arrayList;
	LinkedList<Integer> linkedList;
	// 测试数据数组
	int[] arr;

	@Before
	public void init() {// 测试初始化
		treeSet = new TreeSet<>();
		hashSet = new HashSet<>();
		arrayList = new ArrayList<>();
		linkedList = new LinkedList<>();
		int n = 100000;// 测试数据量
		arr = generateRandomArray(n, n);// arr数组赋随机值
		System.out.println("data size : " + n);
	}

	public void clearAll() {// 清除所有集合类元素
		treeSet.clear();
		hashSet.clear();
		arrayList.clear();
		linkedList.clear();
	}

	public void addAll() {// 把arr的元素添加到所有集合类元素
		for (int i = 0; i < arr.length; i++) {
			treeSet.add(arr[i]);
			hashSet.add(arr[i]);
			arrayList.add(arr[i]);
			linkedList.add(arr[i]);
		}
	}

	@Test
	public void testListAdd() {// 测试List的头部添加和尾部添加
		System.out.println("---------------test List Add---------------");
		testList("ArrayList add first", ListTestType.ADD_FIRST, arrayList, arr);
		testList("LinkedList add first", ListTestType.ADD_FIRST, linkedList, arr);

		clearAll();
		testList("ArrayList add last", ListTestType.ADD_LAST, arrayList, arr);
		testList("LinkedList add last", ListTestType.ADD_LAST, linkedList, arr);

	}

	@Test
	public void testListGet() {// 测试List的get和set方法
		addAll();
		System.out.println("------------test List Get and Set-----------");
		testList("ArrayList get", ListTestType.GET, arrayList, arr);
		testList("LinkedList get", ListTestType.GET, linkedList, arr);

		testList("ArrayList set", ListTestType.SET, arrayList, arr);
		testList("LinkedList set", ListTestType.SET, linkedList, arr);
	}

	@Test
	public void testListRemove() {// 测试List删除头部和尾部元素
		System.out.println("------test List Remove First and Last--------");
		addAll();
		testList("ArrayList remove first", ListTestType.REMOVE_FIRST, arrayList, arr);
		testList("LinkedList remove first", ListTestType.REMOVE_FIRST, linkedList, arr);

		addAll();
		testList("ArrayList remove last", ListTestType.REMOVE_LAST, arrayList, arr);
		testList("LinkedList remove last", ListTestType.REMOVE_LAST, linkedList, arr);
	}

	@Test
	public void testAdd() {// 测试List和Set默认添加元素
		System.out.println("-------------test List and Set Add ----------");
		testSet("TreeSet add", SetTestType.ADD, treeSet, arr);
		testSet("HashSet add", SetTestType.ADD, hashSet, arr);
		testList("ArrayList add", ListTestType.ADD, arrayList, arr);
		testList("LinkedList add", ListTestType.ADD, linkedList, arr);
	}

	@Test
	public void testSetContain() {// 测试List和Set查询已知元素的效率
		addAll();
		Arrays.sort(arr);// 排序后再测试，否则linkedList效率奇高

		System.out.println("-------------test List and Set Contain ----------");
		testSet("TreeSet contain", SetTestType.CONTAIN, treeSet, arr);
		testSet("HashSet contain", SetTestType.CONTAIN, hashSet, arr);

		testList("ArrayList contain", ListTestType.CONTAIN, arrayList, arr);
		testList("LinkedList contain", ListTestType.CONTAIN, linkedList, arr);
	}

	@Test
	public void testSetRemove() {// 测试List和Set的删除已知元素的效率
		addAll();
		Arrays.sort(arr);// 排序后再测试，否则linkedList效率奇高

		System.out.println("-------------test List and Set Remove ----------");
		testSet("TreeSet remove", SetTestType.REMOVE, treeSet, arr);
		testSet("HashSet remove", SetTestType.REMOVE, hashSet, arr);

		testList("ArrayList remove ", ListTestType.REMOVE, arrayList, arr);
		testList("LinkedList remove", ListTestType.REMOVE, linkedList, arr);
	}

	@After
	public void finish() {
		System.out.println("----------------test finished------------------");
		System.out.println();
	}

	/**
	 * 测试结果：
	 * 
	 * <pre>
			data size : 100000
			-------------test List and Set Add ----------
			TreeSet add : 0.0538622s
			HashSet add : 0.0199957s
			ArrayList add : 0.0093683s
			LinkedList add : 0.0038132s
			----------------test finished------------------
			
			data size : 100000
			-------------test List and Set Contain ----------
			TreeSet contain : 0.0144333s
			HashSet contain : 0.008255s
			ArrayList contain : 3.8769868s
			LinkedList contain : 14.0570774s
			----------------test finished------------------
			
			data size : 100000
			------test List Remove First and Last--------
			ArrayList remove first : 0.418418s
			LinkedList remove first : 0.0029727s
			ArrayList remove last : 0.0016742s
			LinkedList remove last : 0.0041506s
			----------------test finished------------------
			
			data size : 100000
			---------------test List Add---------------
			ArrayList add first : 0.4233783s
			LinkedList add first : 0.0026368s
			ArrayList add last : 7.229E-4s
			LinkedList add last : 6.533E-4s
			----------------test finished------------------
			
			data size : 100000
			------------test List Get and Set-----------
			ArrayList get : 6.858E-4s
			LinkedList get : 14.2038765s
			ArrayList set : 0.0016783s
			LinkedList set : 14.7769261s
			----------------test finished------------------
			
			data size : 100000
			-------------test List and Set Remove ----------
			TreeSet remove : 0.0187587s
			HashSet remove : 0.0089649s
			ArrayList remove  : 8.9693051s
			LinkedList remove : 54.5950042s
			----------------test finished------------------
	 * 
	 * </pre>
	 */
}
