package Stack.MonotonousStack._503_NextGreaterElementsII_LoopArray;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 503. 下一个更大元素 II
 *      给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * 示例 1:
 *      输入: [1,2,1]
 *      输出: [2,-1,2]
 * 示例 2：
 *      输入：[2, 1, 2, 4, 3]
 *      输出：[4, 2, 4, -1, 3]
 */
public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }
        int length = nums.length;
        int[] res = new int[length];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new LinkedList<>();
        // 对于一个环形数组里的元素，下一个比这个元素大的元素不一定都在这个元素的左边，也有可能在右边，因此倍增这个数组
        // [2, 1, 2, 4, 3] ---> [2, 1, 2, 4, 3, 2, 1, 2, 4, 3]
        for(int i = 0; i < 2 * length; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i % length]) {
                res[stack.pop() % length] = nums[i % length];  // 通过求模模拟出环形数组的效果
            }
            stack.push(i % length);
        }
        return res;
    }
}
