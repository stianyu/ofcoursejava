package StringProcess.UsingStack._394_DecodeString;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 394. 字符串解码
 *      给定一个经过编码的字符串，返回它解码后的字符串。
 *      编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *      你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *      此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 示例:
 *      s = "3[a]2[bc]", 返回 "aaabcbc".
 *      s = "3[a2[c]]", 返回 "accaccacc".
 *      s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

 */
public class Solution {
    public String decodeString(String s) {
        if(s == null || s.length() == 0) {
            return "";
        }
        Deque<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == ']') {  // 遇到 ] 时，对当前 [] 内字符串进行处理
                char c;
                StringBuilder str = new StringBuilder();  // 保存 [] 里的字符串
                while((c = stack.pop()) != '[') {
                    str.append(c);
                }
                str.reverse();
                StringBuilder strNum = new StringBuilder();  // 保存 [] 前的数字字符串
                while(!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    strNum.append(stack.pop());
                }
                strNum.reverse();
                int count = strNum.length() == 0? 0 : Integer.parseInt(strNum.toString());
                for(int j = 0; j < count; j++) {  // 对 [] 内的字符串解码（重复入栈）
                    for(char ch : str.toString().toCharArray()) {
                        stack.push(ch);
                    }
                }
            } else {
                stack.push(chars[i]);
            }
        }
        StringBuilder res = new StringBuilder();  // 最后处理完 [] 的 stack 保存的字符串就是结果，先进后出
        while(!stack.isEmpty()) {
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String s = "10[a]2[bc]";
        Solution solution = new Solution();
        System.out.println(solution.decodeString(s));
    }
}
