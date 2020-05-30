package StringProcess.UsingStack._20_BracketIsValid;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 20. 有效的括号
 *      给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *  有效字符串需满足：
 *      左括号必须用相同类型的右括号闭合。
 *      左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *  示例 1:
 *      输入: "()"
 *      输出: true
 * 示例 2:
 *      输入: "()[]{}"
 *      输出: true
 * 示例 3:
 *      输入: "(]"
 *      输出: false
 */
public class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>(s.length());
        char[] chs = s.toCharArray();
        for(int i = 0; i < chs.length; i++) {
            if(chs[i] == ')') {
                if(stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if(chs[i] == '}') {
                if(stack.isEmpty() || stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if(chs[i] == ']') {
                if(stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            } else {
                stack.push(chs[i]);
            }
        }
        return stack.isEmpty();
    }
}
