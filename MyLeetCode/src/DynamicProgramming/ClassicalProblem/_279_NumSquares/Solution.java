package DynamicProgramming.ClassicalProblem._279_NumSquares;

import java.util.Arrays;

/**
 * 279. 完全平方数
 *      给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 示例 1:
 *      输入: n = 12
 *      输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *      输入: n = 13
 *      输出: 2
 * 解释: 13 = 4 + 9.
 */
public class Solution {
    // 完全背包问题
    public int numSquares(int n) {
        // dp[i] : 和等于 i 需要的最小完全平方数个数
        int[] dp = new int[n+1];

        Arrays.fill(dp, n+1);
        dp[0] = 0;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }

        return dp[n];
    }
}
