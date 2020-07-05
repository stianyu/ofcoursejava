package DynamicProgramming.ClassicalProblem._5_LongestPalindrome;

/**
 * 5. 最长回文子串
 *      给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 *      输入: "babad"
 *      输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *      输入: "cbbd"
 *      输出: "bb"
 *
 */
public class Solution {
    public String longestPalindrome(String s) {
        // 状态定义为：原字符串的子串是否是回文子串
        int len = s.length();
        if(len < 2) {
            return s;
        }

        int begin = 0;
        int maxLen = 1;

        boolean[][] dp = new boolean[len][len];
        char[] chars = s.toCharArray();

        // 初始化
        for(int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        // 状态转移方程
        for(int r = 1; r < len; r++){
            for(int l = 0; l < r; l++) {
                if(chars[l] != chars[r]){
                    dp[l][r] = false;
                } else {
                    if(r - l < 3){
                        dp[l][r] = true;
                    } else {
                        dp[l][r] = dp[l+1][r-1];
                    }
                }

                // 更新输出
                if(dp[l][r] && r - l + 1 > maxLen) {
                    begin = l;
                    maxLen = r - l + 1;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }
}
