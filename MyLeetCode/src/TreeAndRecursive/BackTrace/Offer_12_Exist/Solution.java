package TreeAndRecursive.BackTrace.Offer_12_Exist;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer 12. 矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *
 */
public class Solution {
    int[][] positions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }

        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == word.charAt(0) && isExist(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isExist(char[][] board, int i, int j, String word, int index, boolean[][] visited) {
        if (index == word.length()) {
            return false;
        }

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || !visited[i][j]) {
            return false;
        }

        visited[i][j] = true;
        boolean hasPath = false;
        hasPath = isExist(board, i + 1, j, word,index + 1, visited)
                || isExist(board, i - 1, j, word, index + 1, visited)
                || isExist(board, i, j + 1, word,index + 1, visited)
                || isExist(board, i, j - 1, word, index + 1, visited);
        if (!hasPath) {
            visited[i][j] = false;
            return false;
        }

        return true;
    }
}
