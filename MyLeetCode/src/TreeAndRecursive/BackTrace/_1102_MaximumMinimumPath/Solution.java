package TreeAndRecursive.BackTrace._1102_MaximumMinimumPath;

/**
 * 1102. 得分最高的路径
 * 给你一个 R 行 C 列的整数矩阵 A。矩阵上的路径从 [0,0] 开始，在 [R-1,C-1] 结束。
 * 路径沿四个基本方向（上、下、左、右）展开，从一个已访问单元格移动到任一相邻的未访问单元格。
 * 路径的得分是该路径上的 最小 值。例如，路径 8 →  4 →  5 →  9 的值为 4 。
 * 找出所有路径中得分 最高 的那条路径，返回其 得分。
 *
 * 输入：[[5,4,5],[1,2,6],[7,4,6]]
 * 输出：4
 * 解释：
 *      得分最高的路径用黄色突出显示。
 */
public class Solution {

    static int maxLen = Integer.MIN_VALUE;
    static boolean[][] visited;
    static int[][] positions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {

        int[][] nums = {{5, 4, 5}, {1, 2, 6}, {7, 4, 6}};
        visited = new boolean[nums.length][nums[0].length];

        System.out.println(maximumMinimumPath(nums));
    }

    public static int maximumMinimumPath(int[][] nums) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                maxLen = Math.max(dfs(nums, i, j), maxLen);
            }

        }
        return -1;
    }

    private static int dfs(int[][] nums, int i, int j) {
        int sum = 0;
        if (!visited[i][j]) {
            visited[i][j] = true;
            for (int[] pos : positions) {
                int newX = i + pos[0];
                int newY = j + pos[1];
                if (newX >= 0 && newX < nums.length && newY >= 0 && newY < nums[0].length && !visited[newX][newY] && nums[newX][newY] != 0)
                    sum += dfs(nums, newX, newY);
            }
            visited[i][j] = false;
        }
        return sum;
    }
}
