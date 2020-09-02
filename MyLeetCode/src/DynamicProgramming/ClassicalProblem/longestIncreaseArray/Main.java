package DynamicProgramming.ClassicalProblem.longestIncreaseArray;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[] nums = {1,3,5,4,7};
        System.out.println(Arrays.toString(findLongest(nums)));
    }

    public static int[] findLongest(int[] nums) {
        if(nums == null || nums.length == 0) return new int[0];
        int resBegin = 0, begin = 0;
        int resEnd = 0, end = 0;
        int maxLength = 1;
        int min = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i-1]) {
                end = i;
            } else {
                begin = i;
            }
            if(end - begin +1 > maxLength) {
                resBegin = begin;
                resEnd = end;
                maxLength = end - begin +1;
            }
        }
        int[] res = new int[maxLength];
        for(int i = resBegin; i <= resEnd; i++) {
            res[i-resBegin] = nums[i];
        }
        return res;

    }
}
