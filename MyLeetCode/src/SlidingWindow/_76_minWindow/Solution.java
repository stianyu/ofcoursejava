package SlidingWindow._76_minWindow;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String minWindow(String s, String t) {
        if(t.length() == 0) {
            return "";
        }

        int l = 0, r = 0;  // [l, r)，初始时窗口为空
        Map<Character,Integer> map = new HashMap<>();
        int count = 0;
        int minLen = Integer.MAX_VALUE, minL = 0, minR = 0;

        // 当窗口为空时，map 初始化，窗口与 t 相比，每个 key 差 value 个
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

            while(count == t.length()) {  // 窗口缩小，l++，更新结果
                if(r - l < minLen) {
                    minR = r;
                    minL = l;
                    minLen = r - l;
                }
                char lc = s.charAt(l++);
                int temp2 = map.get(lc);
                map.put(lc, temp2 + 1);  // 移出一个，相应的 value ++
                if(temp2 + 1 > 0) {  // 由于初始化保证了一旦 value 大于 0，说明该 key 是 t 中需要的元素，此时移出需要count--
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
        int minL = 0, minR = 0;

        for(char ch : t.toCharArray()) {
            freq[ch]++;
        }

        while(r < s.length()) {
            char rc = s.charAt(r++);
            if(--freq[rc] >= 0) {
                count++;
            }

            while(count == t.length()) {
                if(r - l < minR - minL) {
                    minR = r;
                    minL = l;
                }
                char lc = s.charAt(l++);
                if(++freq[lc] > 0) {
                    count--;
                }
            }
        }
        return minR - minL == 0 ? "" : s.substring(minL, minR);
    }
}
