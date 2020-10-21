package BFS._286_WallsAndGates;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 286. 墙与门
 * 你被给定一个 m × n 的二维网格，网格中有以下三种可能的初始化值：
 * -1 表示墙或是障碍物
 * 0 表示一扇门
 * INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
 * 你要给每个空房间位上填上该房间到 最近 门的距离，如果无法到达门，则填 INF 即可。
 *
 *
 * 示例：
 * 给定二维网格：
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 *
 * 运行完你的函数后，该网格应该变成：
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 */
public class Solution {
    static int[][] positions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // dfs
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int i, int j, int depth) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] == -1) {
            return;
        }
        if (rooms[i][j] > depth || depth == 0) {
            rooms[i][j] = depth;
            dfs(rooms, i + 1, j, depth + 1);
            dfs(rooms, i - 1, j, depth + 1);
            dfs(rooms, i, j + 1, depth + 1);
            dfs(rooms, i, j - 1, depth + 1);
        }
    }

    // bfs
    public static void wallsAndGates2(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, i, j);
                }
            }
        }
    }

    private static void bfs(int[][] rooms, int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            for (int[] pos : positions) {
                int newX = top[0] + pos[0];
                int newY = top[1] + pos[1];
                if (newX >= 0 && newX < rooms.length && newY >= 0 && newY < rooms[0].length && rooms[newX][newY] >= rooms[top[0]][top[1]] + 1) {
                    rooms[newX][newY] = rooms[top[0]][top[1]] + 1;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] rooms = {{Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE}};
        wallsAndGates2(rooms);
    }
}