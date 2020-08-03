package DynamicProgramming._474_FindMaxFrom_01package;

/**
 * 474. 一和零
 *      在计算机界中，我们总是追求用有限的资源获取最大的收益。
 *      现在，假设你分别支配着 m 个 0 和 n 个 1。另外，还有一个仅包含 0 和 1 字符串的数组。
 *      你的任务是使用给定的 m 个 0 和 n 个 1 ，找到能拼出存在于数组中的字符串的最大数量。每个 0 和 1 至多被使用一次。
 * 注意:
 *      给定 0 和 1 的数量都不会超过 100。
 *      给定字符串数组的长度不会超过 600。
 * 示例 1:
 *      输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 *      输出: 4
 *      解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * 示例 2:
 *      输入: Array = {"10", "0", "1"}, m = 1, n = 1
 *      输出: 2
 *      解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 */
public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        int[][] dp = new int[m+1][n+1];
        /*
        转换成01背包问题
        状态定义：
        dp[i][j][k] 表示看字符串0~i,使用0~j个0，使用0~k个1，拼出的字符串的最大数量
        状态转移：
        dp[i][j][k] = dp[i-1][j][k] 不考虑第i个字符串，则能拼出dp[i-1][j][k]个字符串
        dp[i][j][k] = dp[i-1][j-use0[i]][k-use1[i]] + 1 考虑第i个字符串

        后降一维dp的技巧是从后向前填表
        */
        for(int i = 1; i <= length; i++) {
            int[] zeroAndOne = countZeroAndOne(strs[i-1]);
            for(int j = m; j >= zeroAndOne[0]; j--) {
                for(int k = n; k >= zeroAndOne[1]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j-zeroAndOne[0]][k-zeroAndOne[1]] + 1);
                }
            }
        }

        return dp[m][n];
    }

    public int[] countZeroAndOne(String str) {
        int[] zeroAndOne = new int[2];
        for(char c:str.toCharArray()) {
            zeroAndOne[c-'0']++;
        }
        return zeroAndOne;
    }
}
