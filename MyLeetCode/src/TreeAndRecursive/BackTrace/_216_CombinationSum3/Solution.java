package TreeAndRecursive.BackTrace._216_CombinationSum3;

import java.util.LinkedList;
import java.util.List;

/**
 * 216. 组合总和 III
 *      找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 说明：
 *      所有数字都是正整数。
 *      解集不能包含重复的组合。 
 * 示例 1:
 *      输入: k = 3, n = 7
 *      输出: [[1,2,4]]
 * 示例 2:
 *      输入: k = 3, n = 9
 *      输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
class Solution {
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new LinkedList<>();
        if(k < 0 || n < 0) {
            return res;
        }

        backTrack(k, 1, n, new LinkedList<>());

        return res;
    }

    private void backTrack(int k, int index, int n, List<Integer> list) {
        if(n == 0 && list.size() == k) {
            res.add(new LinkedList<>(list));
            return;
        }

        for(int i = index; i <= 9; i++) {
            if(i <= n) {
                list.add(i);
                backTrack(k, i + 1, n - i, list);
                list.remove(list.size() - 1);
            }
        }
    }
}
