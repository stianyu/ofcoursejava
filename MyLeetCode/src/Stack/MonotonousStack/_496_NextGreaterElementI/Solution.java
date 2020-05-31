package Stack.MonotonousStack._496_NextGreaterElementI;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 496. 下一个更大元素 I
 * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * 示例 1:
 *      输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 *      输出: [-1,3,-1]
 * 示例 2:
 *      输入: nums1 = [2,4], nums2 = [1,2,3,4].
 *      输出: [3,-1]
 */
public class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 单调递减栈
        if(nums1.length == 0 ) {
            return new int[0];
        }
        Deque<Integer> stack = new LinkedList<>();
        // 用 HashMap 是为了在得到 nums2 对应的右侧第一个大元素的数组后，方便 num1 根据结果找到需要返回的值
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[nums1.length];
        for(int i = 0; i < nums2.length; i++) {
            while(!stack.isEmpty() && stack.peek() < nums2[i]) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        for(int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
