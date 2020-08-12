package DynamicProgramming._32_LongestValidParentheses;

/**
 * 32. 最长有效括号
 *      给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *      输入: "(()"
 *      输出: 2
 * 解释: 最长有效括号子串为 "()"
 *
 * 示例 2:
 *      输入: ")()())"
 *      输出: 4
 * 解释: 最长有效括号子串为 "()()"
 */
public class Solution {
    public int longestValidParentheses(String s) {
        // 这种连续子串的题，状态定义的时候的要求应该苛刻，强调连续，dp[i]为以s[i]结尾最长的有效括号的子串。
        // 因此有 s[i] = '(' , dp[i] = 0;
        int[] dp = new int[s.length()];
        char[] chs = s.toCharArray();
        int max = 0;
        for(int i = 1; i < s.length(); i++) {
            // chs[i] = '(' 直接dp[i] = 0 是默认值
            if(chs[i] == ')') {
                if(i - 1 >= 0) {
                    // chs[i-1] = '('
                    if(chs[i - 1] == '(') {
                        if(i - 2 >= 0) {
                            dp[i] = 2 + dp[i-2];
                        } else {
                            dp[i] = 2;
                        }
                    } else {
                        // chs[i-1] = ')'
                        if(i - dp[i - 1] - 1 >= 0 && chs[i - dp[i - 1] - 1] == '(') {
                            if(i - dp[i-1] - 2  >= 0) {
                                dp[i] = 2 + dp[i - 1] + dp[i - dp[i-1] - 2];
                            } else {
                                dp[i] = 2 + dp[i - 1];
                            }
                        }
//                        else { // chs[i-1] = '(' 这里应该是0
//                            dp[i] = dp[i - 1];
//                        }
                    }
                }
            }
            max = Math.max(dp[i], max);
        }

        return max;
    }
}