package GraphDFSAndBFS._994_orangesRotting;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    int step = 0;

    public int orangesRotting(int[][] grid) {
        if(grid == null || grid.length == 0) {
            return -1;
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2) {
                    dfs(grid, i, j);
                }
            }
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return step;
    }

    public void dfs(int[][] grid, int i, int j) {
//        if(i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[]) {
//
//        }
    }
}

class SolutionBFS {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid[0].length == 0) {
            return 0;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int[][] positions = {{-1,0}, {1,0}, {0,1}, {0,-1}};
        int m = grid.length;
        int n = grid[0].length;
        int step = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    grid[i][j] = 0;
                    while(!queue.isEmpty()) {
                        int[] pop = queue.poll();
                        boolean flag = false;
                        for(int[] position : positions) {
                            int x = pop[0] + position[0];
                            int y = pop[1] + position[1];
                            if(x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1 ) {
                                queue.offer(new int[]{x, y});
                                grid[x][y] = 0;
                                flag = true;
                            }
                        }
                        if(flag) {
                            step++;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return step;
    }
}