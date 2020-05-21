package my.algorithms;

import java.util.Arrays;
import java.util.Random;

public class Sort {
    private Sort() {
    }

    // 选择排序
    public static void selectSort(int[] arr) {
        if (arr == null) {
            return;
        }

        // 寻找[i, n)区间里的最小值
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }

    }

    // 插入排序
    public static void insertionSort(int[] arr) {
        if (arr == null) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if(arr[j] < arr[j - 1]) {
                    swap(arr, j, j -1);
                } else {
                    break;
                }
            }

        }
    }

    // 归并排序
    public static void mergeSort(int[] arr) {
        if (arr == null) {
            return;
        }

        mergeSort(arr, 0, arr.length - 1);
    }

    // 对arr[]数组中[start, end]的元素进行排序
    private static void mergeSort(int[] arr, int start, int end) {
        if(start >= end) {
            return;
        }

//        // 优化2：一个小数组是一个近乎有序的数组的可能性比较大，因此插入排序的效果可能会更好。当这个数组的长度小于等于16的时候可以用插入排序。
//        if(end - start <= 15) {
//            insertionSort(arr);
//            return;
//        }

        int mid = start + (end - start) / 2;
        mergeSort(arr, start, mid); // 先对[start, mid]排好序
        mergeSort(arr, mid + 1, end);  // 再对[mid + 1, end] 排序

        if (arr[mid] > arr[mid+1]) {  // 优化1：如果数据可能是近乎有序的数组，可以加一个判断。
            merge(arr, start, mid, end);
        }
//        int[] array = new int[end - start + 1];
//        int i = start;
//        int j = mid + 1;
//        int k = 0;
//        while (i <= mid && j <= end) {
//            if(arr[i] < arr[j]) {
//                array[k] = arr[i];
//                k++;
//                i++;
//            } else {
//                array[k] = arr[j];
//                k++;
//                j++;
//            }
//        }
//        if (i > mid) {
//            while (j <= end) {
//                array[k] = arr[j];
//                k++;
//                j++;
//            }
//        } else {
//            while (i <= mid) {
//                array[k] = arr[i];
//                k++;
//                i++;
//            }
//        }
//
//        for (int l = start; l <= end; l++) {
//            arr[l] = array[l-start];
//
//        }

    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] array = Arrays.copyOfRange(arr, start, end + 1);
        int i = start;
        int j = mid + 1;
        for (int k = start; k < end+1; k++) {
            if(i > mid) {
                arr[k] = array[j - start];
                j++;
            } else if (j > end) {
                arr[k] = array[i - start];
                i++;
            } else if (array[i - start] < array[j-start]) {
                arr[k] = array[i - start];
                i++;
            } else {
                arr[k] = array[j - start];
                j++;
            }

        }
    }

    // 快速排序（随机快排）
    public static void quickSort(int[] arr) {
        if (arr == null) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);

    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int p = partition(arr, start, end);
        quickSort(arr, start, p-1);
        quickSort(arr, p+1, end);

    }

    private static int partition(int[] arr, int start, int end) {
        Random random = new Random();
        swap(arr, start, random.nextInt(end - start + 1) + start);

        int v = arr[start];

        //p 要把arr分成[start+1, j], [j+1, i)这个区间内
        int j = start;
        for (int i = start+1; i <= end ; i++) {
            if (arr[i] < v) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, start, j);

        return j;
    }

    // 快速排序（双路随机快排）
    public static void quickSort2(int[] arr) {
        if (arr == null) {
            return;
        }

        quickSort2(arr, 0, arr.length - 1);
    }

    private static void quickSort2(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int p = partition2(arr, start, end);
        quickSort2(arr, start, p - 1);
        quickSort2(arr, p + 1, end);

    }

    private static int partition2(int[] arr, int start, int end) {
//        Random random = new Random();
//        swap(arr, start, random.nextInt(end - start + 1) + start);

        int v = arr[start];

        // [l+1, i) <= v; (j, r] >= v
        int i = start + 1, j = end;
        while (true) {
            while (i <= end && arr[i] < v) i++;
            while (j >= start + 1 && arr[j] > v) j--;
            if (i > j) break;
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, j, start);

        return j;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
