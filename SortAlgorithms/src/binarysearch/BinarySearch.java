package binarysearch;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{0,1,2,3,4,5,6};
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(arr, -1));
        System.out.println(rercursiveSearch(arr, -1));
    }

    //标准的二分查找
    private static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target < arr[mid]) {
                r = mid - 1;
            } else if (target > arr[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    //递归写法
    private static int rercursiveSearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return helperBinarySearch(arr, 0, arr.length - 1, target);
    }

    private static int helperBinarySearch(int[] arr, int l, int r, int target) {
        if (l > r) {
            return -1;
        }
        int mid = l + (r - l) / 2;
        if (target < arr[mid]) {
            return helperBinarySearch(arr, l, mid-1, target);
        } else if(target > arr[mid]) {
            return helperBinarySearch(arr, mid + 1, r, target);
        } else {
            return mid;
        }
    }

    //非标准递归
    private static int binarySearch2(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (target < arr[mid]) {
                r = mid - 1;
            } else if (target > arr[mid]) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        if (arr[l] != target) {
            return -1;
        }
        return l;
    }
}
