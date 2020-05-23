package BinarySearch._702_SearchUnknownArray;

interface ArrayReader {
    public int get(int index);
}
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
