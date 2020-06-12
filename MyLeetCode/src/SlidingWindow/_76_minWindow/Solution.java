package SlidingWindow._76_minWindow;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String minWindow(String s, String t) {
        if(t.length() == 0) {
            return "";
        }

        int l = 0, r = 0;
        Map<Character,Integer> map = new HashMap<>();
        int count = 0;
        int minLen = Integer.MAX_VALUE, minL = 0, minR = 0;

        for(char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        while(r < s.length()) {
            char rc = s.charAt(r++);
            int temp = map.getOrDefault(rc, 0);
            map.put(rc, temp - 1);
            if(temp - 1 >= 0) {
                count++;
            }

            while(count == t.length()) {
                if(r - l < minLen) {
                    minR = r;
                    minL = l;
                    minLen = r - l;
                }
                char lc = s.charAt(l++);
                int temp2 = map.get(lc);
                map.put(lc, temp2 + 1);
                if(temp2 + 1 > 0) {
                    count--;
                }
            }
        }
        return minLen == 0 ? "" : s.substring(minL, minR);
    }

    public String minWindow2(String s, String t) {
        if(t.length() == 0) {
            return "";
        }

        int l = 0, r = 0;
        int[] freq = new int[256];
        int count = 0;
        int minLen = Integer.MAX_VALUE, minL = 0, minR = 0;

        for(char ch : t.toCharArray()) {
            freq[ch]++;
        }

        while(r < s.length()) {
            char rc = s.charAt(r);
            r++;
            freq[rc]--;
            if(freq[rc] >= 0) {
                count++;
            }

            while(count == t.length()) {
                if(r - l < minLen) {
                    minR = r;
                    minL = l;
                    minLen = r - l;
                }
                char lc = s.charAt(l);
                freq[lc]++;
                l++;
                if(freq[lc] > 0) {
                    count--;
                }
            }
        }
        return s.substring(minL, minR);
    }
}
