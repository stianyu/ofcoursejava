package SlidingWindow.Offer48_lengthOfLongestSubstring;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        String str = " ";
        Deque<Character> deque = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (deque.contains(c)) {
                while (!deque.isEmpty() && deque.peek() != c) {
                    deque.poll();
                }
                deque.poll();
                deque.add(c);
            } else {
                deque.add(c);
            }
            res = Math.max(res, deque.size());
        }
        System.out.println(res);
    }
}
