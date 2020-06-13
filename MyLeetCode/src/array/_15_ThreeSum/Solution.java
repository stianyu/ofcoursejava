package array._15_ThreeSum;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 15. 三数之和
 *      给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：
 *      给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if(nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                break;
            }
            int l = i + 1, r = nums.length - 1;
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            while(l < r) {
                if(nums[i] + nums[l] + nums[r] == 0) {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    res.add(list);
                    l++;
                    r--;
                    while(l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    while(l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if(nums[i] + nums[l] + nums[r] < 0) {
                    l++;
                    while(l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                } else {
                    r--;
                    while(l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                }
            }
        }
        return res;
    }
}
