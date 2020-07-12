package array._41_FirstMissingPositive;

/**
 * 41. 缺失的第一个正数
 *      给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *      输入: [1,2,0]
 *      输出: 3
 * 示例 2:
 *      输入: [3,4,-1,1]
 *      输出: 2
 * 示例 3:
 *      输入: [7,8,9,11,12]
 *      输出: 1
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        // 不存在的最小正整数肯定在[1, nums.length + 1]这个范围内
        // 只需要把nums数组中每个元素值在[1, nums.length]范围内的值都回到下标对应的位置上
        for(int i = 0; i < nums.length; i++) {
            // nums[i] != nums[nums[i] - 1] 防止[1,1]进入死循环交换
            while(nums[i] >= 1 && nums[i] != i+1 && nums[i] <= nums.length && nums[i] != nums[nums[i] -1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // 再遍历一遍就能找到缺失的最小值
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i+1) {
                return i+1;
            }
        }

        return nums.length + 1;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}