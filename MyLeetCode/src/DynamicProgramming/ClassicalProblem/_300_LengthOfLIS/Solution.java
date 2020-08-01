package DynamicProgramming.ClassicalProblem._300_LengthOfLIS;

import java.util.Arrays;

/**
 * 300. 最长上升子序列
 *      给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 *      输入: [10,9,2,5,3,7,101,18]
 *      输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *      可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 *      你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        // 使用动态规划，时间复杂度为 O(n^2)
        int[] dp = new int[nums.length]; //dp[i] 表⽰以 nums[i] 这个数结尾的最⻓递增⼦序列的⻓度。
        Arrays.fill(dp, 1);
        int res = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }

        return nums.length == 0? 0: res;
    }

    // 使用二分查找, 蜘蛛纸牌算法 O(nlogn)
    public int lengthOfLIS2(int[] nums) {
        return 0;
    }
}