package array._692_TopKFrequent;

import java.util.*;

/**
 * 692. 前K个高频单词
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * 示例 1：
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 *
 * 示例 2：
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 *     出现次数依次为 4, 3, 2 和 1 次。
 */
public class Solution {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for(String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> queue = new PriorityQueue<>(k, (o1, o2) -> {
            int o1c = map.get(o1);
            int o2c = map.get(o2);
            if(o1c == o2c) {
                return o2.compareTo(o1);
            } else {
                return o1c - o2c;
            }
        });

        for(String word : map.keySet()) {
            queue.offer(word);
            if(queue.size() > k) {
                queue.poll();
            }
        }

        LinkedList<String> res = new LinkedList<>();
        while(!queue.isEmpty()) {
            res.push(queue.poll());
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        System.out.println(solution.topKFrequent(words, k));

    }
}