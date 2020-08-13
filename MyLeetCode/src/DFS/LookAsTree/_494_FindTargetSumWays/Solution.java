package DFS.LookAsTree._494_FindTargetSumWays;

import com.sun.scenario.effect.impl.state.LinearConvolveKernel;

import java.util.LinkedList;
import java.util.List;

/**
 * 494. 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *
 * 提示：
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 */
public class Solution {
    // dfs
    public int findTargetSumWays(int[] nums, int S) {
        return dfs(nums, S, 0);
    }
    // 这题nums每个数都要使用，所以需要用numUsed记录使用的数组个数，要满足target==0时使用了nums.length个元素
    public int dfs(int[] nums, int target, int numUsed) {
        if(target == 0 && numUsed == nums.length) {
            return 1;
        }
        if(numUsed >= nums.length) {
            return 0;
        }
        int res = 0;
        res += dfs(nums, target - nums[numUsed], numUsed+1);
        res += dfs(nums, target + nums[numUsed], numUsed+1);
        return res;
    }
}

class Solution2 {
    int res;
    boolean[] visited;

    // 类似于全排列的dfs
    public int findTargetSumWays2(int[] nums, int S) {
        helper(nums, 0, S, new LinkedList<Integer>());
        return res;
    }

    private void helper(int[] nums, int index, int S, List<Integer> list) {
        if (S == 0 && list.size() == nums.length) {
            res++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(nums[i]);
                helper(nums, i+1, S - nums[i], list);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}
/*
变形01背包
 */
class Solution3 {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        // 问题转换成0-1背包，容量为 (sum + S) / 2;
        int m = (sum + S) / 2;
        if(sum < S || (sum + S) % 2 != 0) {
            return 0;
        }
        int[] dp = new int[m+1];
        dp[0] = 1;
        for(int i = 0; i < nums.length; i++) {
            for(int j = m; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[m];
    }
}