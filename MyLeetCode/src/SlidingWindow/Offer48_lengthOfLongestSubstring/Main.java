package SlidingWindow.Offer48_lengthOfLongestSubstring;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String s = in.nextLine();
            Set<Character> set = new HashSet<>();
            int l = 0, r = 0, res = 0;
            while (r < s.length()) {
                char c = s.charAt(r++);
                while (set.contains(c)) {
                    set.remove(s.charAt(l++));
                }
                set.add(c);
                res = Math.max(res, r - l);
            }
            System.out.println(res);
        }
    }
}
