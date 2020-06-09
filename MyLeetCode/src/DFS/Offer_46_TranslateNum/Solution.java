package DFS.Offer_46_TranslateNum;

/**
 * 面试题46. 把数字翻译成字符串（深度优先搜索，动态规划怎么做？）
 *      给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 示例 1:
 *      输入: 12258
 *      输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * 提示：
 *      0 <= num < 231
 */
public class Solution {  // dfs如何思考时间、空间复杂度？
    public int translateNum(int num) {
        String str = String.valueOf(num);
        return helper(str, 0);
    }
    private int helper(String str, int index) {
        if(index >= str.length() - 1) {
            return 1;
        }
        if(index < str.length() - 1) {
            String strNum = str.substring(index, index + 2);
            if(Integer.parseInt(strNum) >= 10 && Integer.parseInt(strNum) <= 25) {  // 506 输出应为 1，06 不成立，所以要限制大于等于10
                return helper(str, index + 1) + helper(str, index + 2);
            }
        }
        return helper(str, index + 1);
    }
}
