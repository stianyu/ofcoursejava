package SlidingWindow._234_MaxInSlidingWindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

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

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums2 = {1, -1};

        System.out.println(Arrays.toString(solution.maxSlidingWindow(nums1, 3)));
        System.out.println(Arrays.toString(solution.maxSlidingWindow(nums2, 1)));
    }
}
