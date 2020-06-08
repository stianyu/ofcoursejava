package BinarySearch._702_SearchUnknownArray;

interface ArrayReader {
    public int get(int index);
}

/**
 * 702. 搜索长度未知的有序数组
 *      给定一个升序整数数组，写一个函数搜索 nums 中数字 target。如果 target 存在，返回它的下标，否则返回 -1。注意，这个数组的大小是未知的。你只可以通过 ArrayReader 接口访问这个数组，ArrayReader.get(k) 返回数组中第 k 个元素（下标从 0 开始）。
 *      你可以认为数组中所有的整数都小于 10000。如果你访问数组越界，ArrayReader.get 会返回 2147483647。
 * 样例 1：
 *      输入: array = [-1,0,3,5,9,12], target = 9
 *      输出: 4
 *      解释: 9 存在在 nums 中，下标为 4
 * 样例 2：
 *      输入: array = [-1,0,3,5,9,12], target = 2
 *      输出: -1
 *      解释: 2 不在数组中所以返回 -1
 * 注释 ：
 *      你可以认为数组中所有元素的值互不相同。
 *      数组元素的值域是 [-9999, 9999]。
 */
class Solution {

    public int search(ArrayReader reader, int target) {
        //先找到右边界
        int index = 1;
        while(reader.get(index) < 2147483647) {
            index = index*10;
        }
        //再二分查找target
        return binarySearch(reader, 0, index, target);
    }

    private int binarySearch(ArrayReader reader, int l, int r, int target) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (target < reader.get(mid)) {
                r = mid - 1;
            } else if (target > reader.get(mid)) {
                l = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
