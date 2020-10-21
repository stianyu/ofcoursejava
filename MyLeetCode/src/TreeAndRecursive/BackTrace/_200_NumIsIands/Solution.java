package TreeAndRecursive.BackTrace._200_NumIsIands;

/**
 * 200. 岛屿数量
 *      给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *      岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 *      此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1:
     * 输入:
     * [
     * ['1','1','1','1','0'],
     * ['1','1','0','1','0'],
     * ['1','1','0','0','0'],
     * ['0','0','0','0','0']
     * ]
     * 输出: 1
 * 示例 2:
     * 输入:
     * [
     * ['1','1','0','0','0'],
     * ['1','1','0','0','0'],
     * ['0','0','1','0','0'],
     * ['0','0','0','1','1']
     * ]
     * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 */
public class Solution {
    // floodFill 算法
    int[][] position = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int res;

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) {
            return res;
        }

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] == '1') {
                    res++;
                    dfs(grid, row, col);
                }
            }
        }

        return res;
    }

    private void dfs(char[][] grid, int row, int col) {
        grid[row][col] = '0';
        for(int[] pos : position) {
            int newR = row + pos[0];
            int newC = col + pos[1];
            // 看似没有递归终止条件，其实在这里递归就停止了
            if(newR >= 0 && newR < grid.length && newC >= 0 && newC < grid[0].length && grid[newR][newC] == '1') {
                dfs(grid, newR, newC);
            }
        }
    }
}
