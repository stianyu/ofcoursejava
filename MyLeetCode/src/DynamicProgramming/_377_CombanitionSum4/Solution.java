package DynamicProgramming._377_CombanitionSum4;

/**
 * 377. 组合总和 Ⅳ (可以和 LeetCode 518一起思考)
 *      给定一个由正整数组成且不存在重复数字的数组，找出和为给定目标正整数的组合的个数。
 * 示例:
 *      nums = [1, 2, 3]
 *      target = 4
 * 所有可能的组合为：
 *      (1, 1, 1, 1)
 *      (1, 1, 2)
 *      (1, 2, 1)
 *      (1, 3)
 *      (2, 1, 1)
 *      (2, 2)
 *      (3, 1)
 *      请注意，!!!顺序不同的序列被视作不同的组合。!!!
 *      因此输出为 7。
 * 进阶：
 *      如果给定的数组中含有负数会怎么样？
 *      问题会产生什么变化？
 *      我们需要在题目中添加什么限制来允许负数的出现？
 */
public class Solution {
    public int combinationSum4(int[] nums, int target) {
        if(nums == null || nums.length == 0 || target < 0) {
            return 0;
        }

        //dp[i]代表组合数为i时使用nums中的数能组成的组合数的个数
        //dp[i]=dp[i-nums[0]]+dp[i-nums[1]]+dp[i=nums[2]]+...
        //举个例子比如nums=[1,3,4],target=7;
        //dp[7]=dp[6]+dp[4]+dp[3]
        //其实就是说7的组合数可以由三部分组成，1和dp[6]，3和dp[4],4和dp[3];
        int[] dp = new int[target + 1];
        //dp[1] = 1 + dp[0],如果那个硬币的面值刚刚好等于需要凑出的价值，这个就成为 1 种组合方案
        dp[0] = 1;

        for(int j = 1; j <= target; j++) {
            for(int num : nums) {
                if(j >= num) {
                    dp[j] = dp[j] + dp[j - num];
                }
            }
        }

        return dp[target];
    }
}