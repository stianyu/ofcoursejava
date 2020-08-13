package GraphDFSAndBFS._733_FloodFill;

import java.util.Queue;
import java.util.ArrayDeque;


public class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor) {
            return image;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});
        int[][] position = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int oldColor = image[sr][sc];
        while(!queue.isEmpty()) {
            int[] pop = queue.poll();
            image[pop[0]][pop[1]] = newColor;
            for(int[] childPosition : position) {
                int childRow = pop[0] + childPosition[0];
                int childCol = pop[1] + childPosition[1];
                if(childRow>=0 && childRow<image.length && childCol>=0 && childCol<image[0].length && image[childRow][childCol]==oldColor) {
                    queue.offer(new int[] {childRow, childCol});
                }
            }
        }

        return image;
    }
}