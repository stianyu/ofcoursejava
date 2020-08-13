package GraphDFSAndBFS._695_MaxAreaOfIsland;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 695. 岛屿的最大面积
 *      给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 *      一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *      找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 * 示例 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 * 示例 2:
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 */
public class Solution {
    // dfs : 击败100%
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int max = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int i, int j) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int count = 1;
        count += dfs(grid, i+1, j);
        count += dfs(grid, i, j+1);
        count += dfs(grid, i-1, j);
        count += dfs(grid, i, j-1);
        return count;
    }
}

class Solution2 {
    // bfs ： 击败28%
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid[0].length == 0) {
            return 0;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int[][] positions = {{-1,0}, {1,0}, {0,1}, {0,-1}};
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int count = 0;
                if(grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    grid[i][j] = 0;
                    count++;
                    while(!queue.isEmpty()) {
                        int[] pop = queue.poll();
                        for(int[] position : positions) {
                            int x = pop[0] + position[0];
                            int y = pop[1] + position[1];
                            if(x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1 ) {
                                queue.offer(new int[]{x, y});
                                grid[x][y] = 0;
                                count++;
                            }
                        }
                    }
                    max = Math.max(max, count);
                }
            }
        }

        return max;
    }
}