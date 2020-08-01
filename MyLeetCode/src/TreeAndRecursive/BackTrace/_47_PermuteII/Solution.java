package TreeAndRecursive.BackTrace._47_PermuteII;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class Solution {
    List<List<Integer>> res;
    boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new LinkedList<>();
        visited = new boolean[nums.length];
        if (nums == null || nums.length == 0) {
            return res;
        }

        Arrays.sort(nums);
        backTrack(nums, 0, new LinkedList<>());

        return res;
    }

    private void backTrack(int[] nums, int index, List<Integer> list) {
        if (list.size() == nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;  // 去重
                visited[i] = true;
                list.add(nums[i]);
                backTrack(nums, i + 1, list);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}
