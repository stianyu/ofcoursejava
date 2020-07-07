package TreeAndRecursive.BackTrace._17_LetterCombinations;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 *      给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *      给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 *      输入："23"
 *      输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */

public class Solution {
    List<String> res;
    String[] digitsString = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        if(digits == null || digits.length() == 0) {
            return res;
        }
        helper(digits, 0, "");
        return res;
    }

    private void helper(String digits, int index, String str) {
        System.out.println(index + " : "+ str);
        if(index == digits.length()) {
            res.add(str);
            System.out.println("get : " + str + ", return");
            return;
        }

        String numStr = digitsString[digits.charAt(index) - '0'];
        for(int i = 0; i < numStr.length(); i++) {
            System.out.println("digits[" + index + "] =" +digits.charAt(index) + ", use " + numStr.charAt(i));
            helper(digits, index+1, str + numStr.charAt(i));
        }

        System.out.println("digits[" + index + "] =" + digits.charAt(index) + " complete, return");
    }

    public static void main(String[] args) {
        String digits = "234";
        Solution solution = new Solution();
        System.out.println(solution.letterCombinations(digits));
    }
}
