package DynamicProgramming._198_Rob;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 *      你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *      给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 示例 1:
 *      输入: [1,2,3,1]
 *      输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 */
public class Solution {
    /**
     * 记忆化搜索
     */
    int[] memory;
    public int rob(int[] nums) {
        memory = new int[nums.length];
        Arrays.fill(memory, -1);
        return rob(nums, nums.length - 1);
    }

    public int rob(int[] nums, int n) {
        if(n < 0) {
            return 0;
        }
        if(memory[n] != -1) {
            return memory[n];
        }
        memory[n] = Math.max(rob(nums, n - 1), rob(nums, n - 2) + nums[n]);
        return memory[n];
    }

    /**
     * 动态规划
     */
    public int robDP(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        memory = new int[nums.length];
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        memory[0] = nums[0];
        memory[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            memory[i] = Math.max(memory[i-1], memory[i-2] + nums[i]);
        }
        return memory[nums.length - 1];
    }

    /**
     * 动态规划，使用 O(1) 复杂度
     */
    public int rotDP2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int pre = 0, cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = temp;
        }
        return cur;
    }
}
