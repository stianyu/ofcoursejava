package my.test;
import my.algorithms.Sort;

import java.util.Arrays;
import java.util.Random;

public class SortTest {
    static int[] arr;

    public SortTest(int n) {
        arr = new int[n];
    }

    public static void generateArray(int n) {
        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n);

        }
        arr = array;

    }

    public static void main(String[] args) {
        int n = 10;
        int[] a = new int[n];
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            a[i] = random.nextInt(10);

        }
        int[] b = Arrays.copyOf(a, a.length);
        int[] c = Arrays.copyOf(a, a.length);
        int[] d = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Sort.selectSort(a);
        Sort.insertionSort(b);
        Sort.mergeSort(c);
        Sort.quickSort2(d);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
        System.out.println(Arrays.toString(d));
    }
}
