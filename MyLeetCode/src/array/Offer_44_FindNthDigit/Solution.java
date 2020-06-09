package array.Offer_44_FindNthDigit;

/**
 * 面试题44. 数字序列中某一位的数字
 *      数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *      请写一个函数，求任意第n位对应的数字。
 * 示例 1：
 *      输入：n = 3
 *      输出：3
 * 示例 2：
 *      输入：n = 11
 *      输出：0
 * 限制：
 *      0 <= n < 2^31
 */
public class Solution {
    public int findNthDigit(int n) {
        if (n == 0) {
            return 0;
        }
        int digit = 1;
        long sum = 0;
        while(sum + 9 * digit * (int)Math.pow(10, digit - 1) < n) {
            sum += 9 * digit * (int)Math.pow(10, digit - 1);
            digit++;
        }
        digit--;
        int valueOfDigit = (int) (n - sum);
        return -1;


    }


    public static void main(String[] args) {
        int n = 11;
        Solution solution = new Solution();
        System.out.println(solution.findNthDigit(n));
    }
}
