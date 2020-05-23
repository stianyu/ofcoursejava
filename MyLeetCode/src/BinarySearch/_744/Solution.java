package BinarySearch._744;

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, r = letters.length-1;
        //找>target的最小索引
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(letters[mid] - 'a' > target - 'a') {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l < letters.length ? letters[l] : letters[0];
    }
}
