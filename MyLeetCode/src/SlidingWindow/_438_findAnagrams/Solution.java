package SlidingWindow._438_findAnagrams;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 *      给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *      字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * 说明：
 *      字母异位词指字母相同，但排列不同的字符串。
 *      不考虑答案输出的顺序。
 * 示例 1:
 * 输入:
 *      s: "cbaebabacd" p: "abc"
 * 输出:
 *      [0, 6]
 * 解释:
 *      起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 *      起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 */
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> list = new ArrayList<>();
        int arr[] = new int[26];
        int l = 0, r = 0;
        int count = 0;

        for(char ch : p.toCharArray()) {
            arr[ch - 'a']++;
        }

        while(r < s.length()) {
            char rc = s.charAt(r);
            if(--arr[rc - 'a'] >= 0) {
                count++;
            }

            while(count == p.length()) {
                if(r - l + 1 == p.length()) {
                    list.add(l);
                }
                char lc = s.charAt(l);
                l++;
                if(++arr[lc - 'a'] > 0) {
                    count--;
                }
            }
            r++;
        }

        return list;
    }
}
