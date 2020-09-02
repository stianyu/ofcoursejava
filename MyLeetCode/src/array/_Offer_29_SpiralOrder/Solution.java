package array._Offer_29_SpiralOrder;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 *      输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * 示例 1：
 *      输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *      输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *      输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 *      输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * 限制：
 *      0 <= matrix.length <= 100
 *      0 <= matrix[i].length <= 100
 */
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] res = new int[m * n];
        int u = 0, d = m - 1;
        int l = 0, r = n - 1;
        int count = 0;
        while(true) {
            for(int i = l; i <= r; i++) {
                res[count++] = matrix[u][i];
            }
            if(++u > d) {
                break;
            }
            for(int i = u; i <= d; i++) {
                res[count++] = matrix[i][r];
            }
            if(--r < l) {
                break;
            }
            for(int i = r; i >= l; i--) {
                res[count++] = matrix[d][i];
            }
            if(--d < u) {
                break;
            }
            for(int i = d; i >= u; i-- ) {
                res[count++] = matrix[i][l];
            }
            if(++l > r) {
                break;
            }
        }
        return res;
    }

    public int[] spiralOrder2(int[][] matrix) {
        if(matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] positions = {{0,1}, {1,0}, {0,-1}, {-1, 0}};
        int[] res = new int[m*n];
        int count = 0;
        int i = 0;
        int j = 0;
        int pos = 0;
        while(count < m*n) {
            res[count++] = matrix[i][j];
            visited[i][j] = true;
            int nextI = i + positions[pos][0];
            int nextJ = j + positions[pos][1];
            if(nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || visited[nextI][nextJ]) {
                pos = (pos + 1) % 4;
            }
            i += positions[pos][0];
            j += positions[pos][1];
        }
        return res;
    }
}
