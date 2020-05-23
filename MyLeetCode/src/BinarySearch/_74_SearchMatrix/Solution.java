package BinarySearch._74_SearchMatrix;

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) {
            return false;
        }
        int l = 0, r = matrix.length * matrix[0].length - 1;
        int n = matrix[0].length; //记录当前列数，用来算二维数组的位置
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(matrix[mid/n][mid%n] < target) {
                l = mid + 1;
            } else if(matrix[mid/n][mid%n] > target) {
                r = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
