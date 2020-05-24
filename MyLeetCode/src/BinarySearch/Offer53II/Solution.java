package BinarySearch.Offer53II;

/**
 * 题目：剑指Offer 面试题53 - II. 0～n-1中缺失的数字
 *      一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 *      在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * 示例：
 *      输入: [0,1,2,3,4,5,6,7,9]
 *      输出: 8
 */
class Solution {
    public int missingNumber(int[] nums) {
        // 找值大于索引的最小索引
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] != mid) {
                if(mid == 0 || nums[mid - 1] == mid - 1) {
                    return mid;
                }
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
