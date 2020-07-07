package TreeAndRecursive.BackTrace._77_Combinations;

import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 *      给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 *      输入: n = 4, k = 2
 *      输出:
 *      [
 *        [2,4],
 *        [3,4],
 *        [2,3],
 *        [1,2],
 *        [1,3],
 *        [1,4],
 *      ]
 *
 *  总结：对组合问题来说，顺序是不重要的
 *
 */
public class Solution {
    List<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {
        res = new LinkedList<>();
        if(n <= 0 || k <= 0 || n < k ){
            return res;
        }

        backTrack(n, 1, k, new LinkedList<>());

        return res;
    }

    private void backTrack(int n, int index, int k, List<Integer> list) {
        if(list.size() == k) {
            res.add(new LinkedList<Integer>(list));
            return;
        }

        for(int i = index; i <= n; i++) {
            list.add(i);
            backTrack(n, i + 1, k, list);
            list.remove(list.size() - 1);
        }
    }



}
