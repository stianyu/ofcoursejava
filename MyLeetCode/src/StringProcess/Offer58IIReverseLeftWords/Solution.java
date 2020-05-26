package StringProcess.Offer58IIReverseLeftWords;

/**
 * 题目：
 *      字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，
 *      输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * 示例：
 *      输入: s = "abcdefg", k = 2
 *      输出: "cdefgab"
 *
 * 题解：
 *      1.把字符串分两部分翻转
 *      2.再合起来一起反转
 *
 * 考察点：
 *      1.字符串反转操作
 */
class Solution {
    public String reverseLeftWords(String s, int n) {
        if(s == null || s.length() == 0) {
            return "";
        }
        n = n % s.length();
        char[] chs = s.toCharArray();
        reverse(chs, 0, n - 1);
        reverse(chs, n, chs.length - 1);
        reverse(chs, 0, chs.length - 1);
        return new String(chs);
    }
    private void reverse(char[] arr, int i, int j) {
        while(i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
