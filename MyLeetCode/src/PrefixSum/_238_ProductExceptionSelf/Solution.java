package PrefixSum._238_ProductExceptionSelf;

import java.util.Arrays;

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        // 计算前缀积
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i-1];
        }
        // 计算后缀积
        right[n-1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i+1] * nums[i+1];
        }
        // 计算除了元素 i 的所有元素积就是计算 i 的前缀积和后缀积的积
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = left[i] * right[i];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.productExceptSelf(nums)));
    }
}
