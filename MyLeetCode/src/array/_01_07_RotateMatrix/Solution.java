package array._01_07_RotateMatrix;

/**
 * 面试题 01.07. 旋转矩阵
 *      给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 *      不占用额外内存空间能否做到？
 * 示例 1:
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 */
public class Solution {
    public void rotate(int[][] matrix) {
        int topR = 0, topC = 0;
        int bottomR = matrix.length - 1, bottomC = matrix[0].length - 1;
        while(topR < bottomR) {
            for(int i = 0; i < bottomR-topR; i++) {
                int temp = matrix[topR][topC+i];
                matrix[topR][topC+i] = matrix[bottomR-i][topC];
                matrix[bottomR-i][topC] = matrix[bottomR][bottomC-i];
                matrix[bottomR][bottomC-i] = matrix[topR+i][bottomC];
                matrix[topR+i][bottomC] = temp;
            }
            topC++;
            topR++;
            bottomC--;
            bottomR--;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{ 1, 2, 3}, { 4, 5, 6}, { 7, 8, 9}};
        Solution solution = new Solution();
        solution.rotate(matrix);
        System.out.println(matrix);
    }
}
