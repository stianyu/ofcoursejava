package SlidingWindow._239_MaxInSlidingWindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值
 *      给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *      返回滑动窗口中的最大值。
 * 进阶：
 *      你能在线性时间复杂度内解决此题吗？
 * 示例:
 *      输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 *      输出: [3,3,5,5,6,7]
 * 解释:
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return new int[0];
        }

        Deque<Integer> queue = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 未形成窗口
        for(int i = 0; i < k; i++) {
            // 当双端队列为空，并且双端队列尾部的元素比当前元素小或相等的话，则尾部的元素全部弹出
            while(!queue.isEmpty() && queue.peekLast() < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(nums[i]);
        }

        // 形成窗口
        res[0] = queue.peekFirst();
        for(int i = k; i < nums.length; i++) {
            // 如果当前索引减去窗口大小所得的值等于头部的值，则说明队首不在这个窗口中了
            if(queue.peekFirst() == nums[i-k]) {
                queue.removeFirst();
            }
            while(!queue.isEmpty() && queue.peekLast() < nums[i]) {
                queue.removeLast();
            }
            queue.addLast(nums[i]);
            res[i - k + 1] = queue.peekFirst();
        }
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++) {
            // 如果队首放的索引大于 i - k，说明当前的最大值已经不是该窗口下的最大值，次大值才是
            if(i >= k && queue.peekFirst() == i - k) {  // 加上 i >= k, 可以减少 k 次查询队首
                queue.removeFirst();
            }
            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.removeLast();
            }
            queue.addLast(i);

            if(i >= k - 1) {
                res[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums2 = {1, -1};

        System.out.println(Arrays.toString(solution.maxSlidingWindow(nums1, 3)));
        System.out.println(Arrays.toString(solution.maxSlidingWindow(nums2, 1)));
    }
}
