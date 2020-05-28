package StringProcess.UsingStack._394_DecodeString;

import java.util.Deque;
import java.util.LinkedList;

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
                int count = Integer.parseInt(tempNumber.reverse().toString());
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
}
