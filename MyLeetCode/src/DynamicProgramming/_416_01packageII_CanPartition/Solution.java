package DynamicProgramming._416_01packageII_CanPartition;

import java.util.Arrays;

/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * <p>
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 */
public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if(sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        //dp[j] 表示nums[0 - i]这段中是否满足有集合和为j
        boolean[] dp = new boolean[sum + 1];
        // // 这步初始化的原因是什么？
        dp[0] = true;

        for(int i = 0; i < nums.length; i++) {
            for(int j = sum; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }

        return dp[sum];
    }

    public static void main(String[] args) {
        int[] a = new int[10];

        int sum = 10;

        for (int i = 0; i < a.length; i++) {
            a[i] = Math.max(a[i], sum);
        }

        System.out.println(Arrays.toString(a));
    }
}
