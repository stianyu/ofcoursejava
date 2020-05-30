package Stack.MonotonousStack._84_LargestRectangleArea;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形（单调递增栈）
 *      给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *      求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 */
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] newHeights = new int[heights.length + 2];
        for (int i = 1; i < heights.length + 1; i++) {
            newHeights[i] = heights[i - 1];
        }
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {
                int cur = stack.pop();
                res = Math.max(res, (i - stack.peek() - 1) * newHeights[cur]);
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        Solution solution = new Solution();
        System.out.println(solution.largestRectangleArea(heights));
    }
}
