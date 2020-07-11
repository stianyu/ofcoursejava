package TreeAndRecursive.BackTrace._40_CombinationSum2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 40. 组合总和 II
 *      给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *      candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 *      所有数字（包括目标数）都是正整数。
 *      解集不能包含重复的组合。 
 * 示例 1:
 *      输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 *      [
 *        [1, 7],
 *        [1, 2, 5],
 *        [2, 6],
 *        [1, 1, 6]
 *      ]
 * 示例 2:
 *      输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 *      [
 *        [1,2,2],
 *        [5]
 *      ]
 */
public class Solution {
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new LinkedList<>();
        if(candidates == null || candidates.length == 0 || target < 0) {
            return res;
        }

        Arrays.sort(candidates);
        bachTrack(candidates, 0, target, new LinkedList<>());

        return res;
    }

    private void bachTrack(int[] candidates, int index, int target, List<Integer> list) {
        if(target == 0) {
            res.add(new LinkedList<>(list));
            return;
        }

        for(int i = index; i < candidates.length; i++) {
            if(i > index && candidates[i] == candidates[i - 1]) {  // 去重
                continue;
            }
            if(candidates[i] <= target) {
                list.add(candidates[i]);
                bachTrack(candidates, i + 1, target - candidates[i], list);
                list.remove(list.size() - 1);
            }
        }
    }
}