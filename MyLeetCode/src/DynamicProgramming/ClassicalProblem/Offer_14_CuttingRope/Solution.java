package DynamicProgramming.ClassicalProblem.Offer_14_CuttingRope;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 示例 1：
 *      输入: 2
 *      输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例 2:
 *      输入: 10
 *      输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *      2 <= n <= 58
 */
public class Solution {
    public int cuttingRope(int n) {
        // n < 3 的情况，因为绳子必须要分割
        if(n == 2) return 1;
        if(n == 3) return 2;

        int timesOf3 = n / 3;
        if(n - timesOf3 * 3 == 1) {
            timesOf3 -= 1;
        }
        int timesOf2 = (n - timesOf3 * 3) / 2;
        return (int) ((int) Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }

    public int cuttingRope2(int n) {
        // n < 3 的情况，因为绳子必须要分割
        if(n == 2) return 1;
        if(n == 3) return 2;

        // 跳出循环的条件是 n = 2, 3, 4。2、3不需要继续被分割，4可以被分割为2、2，积还是4。因此，最后是 sum * n
        int sum = 1;
        while(n > 4) {
            sum *= 3;
            n -= 3;
        }
        return sum * n;
    }

    public int cuttingRope3(int n) {
        if(n == 2) return 1;
        if(n == 3) return 2;
        long sum = 1;  // sum设置成long
        while(n > 4) {
            sum = sum * 3 % 1000000007;
            n -= 3;
        }
        return (int)(sum * n % 1000000007); // 最后要转成int
    }
}
