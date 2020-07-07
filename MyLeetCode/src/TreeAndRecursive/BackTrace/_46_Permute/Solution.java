package TreeAndRecursive.BackTrace._46_Permute;

import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列
 *      给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 *      输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Solution {
    List<List<Integer>> res;
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        res = new LinkedList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }

        used = new boolean[nums.length];
        backTrack(nums, 0, new LinkedList<Integer>());

        return res;
    }

    private void backTrack(int[] nums, int index, List<Integer> list) {
        if(index == nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            if(!used[i]) {
                list.add(nums[i]);
                used[i] = !used[i];
                backTrack(nums, index + 1, list);
                used[i] = !used[i];
                list.remove(list.size() - 1);
            }
        }
    }
}
