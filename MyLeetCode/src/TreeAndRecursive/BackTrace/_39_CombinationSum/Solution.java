package TreeAndRecursive.BackTrace._39_CombinationSum;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和
 *      给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *      candidates 中的数字可以无限制重复被选取。
 * 说明：
 *      所有数字（包括 target）都是正整数。
 *      解集不能包含重复的组合。 
 *
 * 示例 1:
 *      输入: candidates = [2,3,6,7], target = 7,
 *      所求解集为:
 *      [
 *        [7],
 *        [2,2,3]
 *      ]
 *
 * 示例 2:
 *      输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 *      [
 *        [2,2,2,2],
 *        [2,3,3],
 *        [3,5]
 *      ]
 */
class Solution {
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new LinkedList<>();
        if(candidates == null || candidates.length == 0 || target < 0 ) {
            return res;
        }

        Arrays.sort(candidates);
        backTrack(candidates, 0, target, new LinkedList<>());

        return res;
    }

    private void backTrack(int[] candidates, int index, int target, LinkedList<Integer> list) {
        if(target == 0) {
            res.add(new LinkedList<>(list));
            return;
        }

        for(int i = index; i < candidates.length; i++) {   // i 从 index 开始
            if(candidates[i] <= target) {
                list.add(candidates[i]);
                backTrack(candidates, i, target - candidates[i], list);  // 注意这里是从 i 开始，而不是 i + 1
                list.remove(list.size() - 1);
            }
        }
    }
}
