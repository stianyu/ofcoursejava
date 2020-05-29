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
        char[] chs = s.toCharArray();
        stack.push(chs[0]);
        int i = 1;
        while(i < s.length()) {
            if(chs[i] == ']') {
                StringBuilder tempBuilder = new StringBuilder();
                char c = stack.pop();
                while(c != '[') {
                    tempBuilder.append(c);
                    c = stack.pop();
                }
                char[] tempArray = tempBuilder.toString().toCharArray();
                StringBuilder tempNumber = new StringBuilder();
                while(!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    tempNumber.append(stack.pop());
                }
                int count = 0;
                char[] tempNumberChar = tempNumber.reverse().toString().toCharArray();
                for (int k = 0; k < tempNumberChar.length ; k++) {
                    count = count * 10 + Integer.parseInt(String.valueOf(tempNumberChar[k]));
                }
                while(count != 0) {
                    for(int j = tempArray.length - 1; j >= 0; j--) {
                        stack.push(tempArray[j]);
                    }
                    count--;
                }
            } else {
                stack.push(chs[i]);
            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String s = "10[a]2[bc]";
        Solution solution = new Solution();
        System.out.println(solution.decodeString(s));
    }
}
