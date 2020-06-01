package Stack.MonotonousStack._85_maximalRectangle;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 85. 最大矩形
 *      给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例:
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class Solution {
    /**
     * 使用单调栈 + dp 的方法，把每一层看程是柱状图，从最底层往上计数柱子的高度，时间复杂度 O(m*n)
     * [
     * ["1","0","1","0","0"],   --->  [1, 0, 1, 0, 0]
     * ["1","0","1","1","1"],   --->  [2, 0, 2, 1, 1]
     * ["1","1","1","1","1"],   --->  [3, 1, 3, 3, 2]
     * ["1","0","0","1","0"]    --->  [4, 0, 0, 3, 0]
     * ]
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int max = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;   // 这个 if - else 语句保证了最后一层为 0 时，柱子的高度是 0
                }
            }
            max = Math.max(max, largestRectangleArea(heights));
        }
        return max;
    }
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
}
