package DynamicProgramming._375_GetMoneyAmount;

/**
 * 375. 猜数字大小 II
 *      我们正在玩一个猜数游戏，游戏规则如下：
 *      我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。
 *      每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。
 *      然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
 * 示例:
 *      n = 10, 我选择了8.
 *
 * 第一轮: 你猜我选择的数字是5，我会告诉你，我的数字更大一些，然后你需要支付5块。
 * 第二轮: 你猜是7，我告诉你，我的数字更大一些，你支付7块。
 * 第三轮: 你猜是9，我告诉你，我的数字更小一些，你支付9块。
 *
 * 游戏结束。8 就是我选的数字。
 * 你最终要支付 5 + 7 + 9 = 21 块钱。
 * 给定 n ≥ 1，计算你至少需要拥有多少现金才能确保你能赢得这个游戏。
 */
public class Solution {
    int[][] memo;
    public int getMoneyAmount(int n) {
        memo = new int[n+1][n+1];
        return recursive(1, n);
    }

    public int recursive(int l, int r) {
        if(l >= r) {
            return 0;
        }
        if(memo[l][r] != 0) {
            return memo[l][r];
        }
        int sum = 0;
        int res = Integer.MAX_VALUE;
        for(int i = l; i <= r; i++) {
            sum = i + Math.max(recursive(l, i - 1), recursive(i + 1, r));
            res = Math.min(sum, res);
        }
        memo[l][r] = res;
        return res;
    }

    public int getMoneyAmount2(int n, int k) {
        memo = new int[n+1][n+1];
        return recursive(1, n, k);
    }

    public int recursive(int l, int r, int k) {
        if(l >= r) {
            return 0;
        }
        if(memo[l][r] != 0) {
            return memo[l][r];
        }
        int res = Integer.MAX_VALUE;
        for(int i = l; i <= r; i++) {
            if (k > 0) {
                res = Math.min(Math.max(recursive(l, i - 1, k - 1), recursive(i + 1, r, k - 1)), res);
                res = Math.min(res, i + Math.max(recursive(l, i - 1, k), recursive(i + 1, r, k)));
            } else {
                res = Math.min(res, i + Math.max(recursive(l, i - 1, k), recursive(i + 1, r, k)));
            }
        }
        memo[l][r] = res;
        return res;
    }
}
