package BinarySearch._34;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *       给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *      你的算法时间复杂度必须是 O(log n) 级别。
 *      如果数组中不存在目标值，返回 [-1, -1]。
 * 示例 1:
 *      输入: nums = [5,7,7,8,8,10], target = 8
 *      输出: [3,4]
 * 示例 2:
 *      输入: nums = [5,7,7,8,8,10], target = 6
 *      输出: [-1,-1]
 */
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        //假设值存在，其实不存在的情况也符合。一定要按照二分查找的标准写法来写，这样不用考虑恼人的边界问题
        int left = searchLeft(nums, target);
        int right = searchRight(nums, target);
        System.out.println(left + ", " + right);
        if (left + 1 > right - 1) {
            return new int[]{-1, -1};
        }
        return new int[]{left+1, right-1};
    }

    //找<target的值的最大索引
    private int searchLeft(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
//            System.out.println(l + ", " + r);
            if (nums[mid] < target) {
                l = mid + 1; // 如果是l = mid,则会陷入死循环
            } else {
                r = mid - 1; //如果nums[mid] > target的话，说明在左边，所以就把r往mid的左边挪一个
            }
        }
        return r;  // 一定会跳出while循环，此时l = r + 1;
    }

    //找>target的值的最小索引
    private int searchRight(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
//            System.out.println(l + ", " + r);
            if (nums[mid] > target) {
                r = mid - 1;  //如果是r = mid,则会陷入死循环
            } else {
                l = mid + 1;  //如果nums[mid] < target的话，说明要找的索引在右边，因此把l往mid往右挪一个
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] array = new int[]{5,7,7,8,8,10};
        Solution solution = new Solution();
        int[] res = solution.searchRange(array, 6);
        System.out.println(Arrays.toString(res));
    }
}
