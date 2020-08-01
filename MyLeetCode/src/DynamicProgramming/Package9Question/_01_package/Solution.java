package DynamicProgramming.Package9Question._01_package;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] v = new int[n+1];
            int[] w = new int[n+1];
            for(int i = 1; i <= n; i++) {
                v[i] = in.nextInt();
                w[i] = in.nextInt();
            }
            System.out.println(solve(n, m, v, w));
        }
    }

    /*
        f[i][j]是选前i个物品，总体积为j的情况下，总价值是多少

        result = max(f[n][0~v])

        状态转移方程：
        1. 不选第i个物品，则f[i][j] = f[i-1][j];
        2. 选第i个物品，则f[i][j] = f[i-1][j-v[i]] + w[i]
        f[i][j] = max(1, 2)



    */
    public static int solve(int n, int m, int[] v, int[] w) {
        int[][] f = new int[n+1][m+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                f[i][j] = f[i-1][j];
                if(v[i] <= j) {
                    f[i][j] = Math.max(f[i][j], f[i-1][j-v[i]] + w[i]);
                }
            }
        }

        int res = 0;
        for(int j = 0; j <= m; j++) {
            res = Math.max(res, f[n][j]);
        }

        return res;
    }

    /*
    为什么这里可以从 m 推到v[i]可以求出来？
    因为初始化是 f[i] = 0; 而不是 f[0] = 0;
     */
    public static int solve2(int n, int m, int[] v, int[] w) {
        int[] f = new int[m+1];  // f[i][j]是看前i个物品，总体积为j的情况下，总价值是多少
        for(int i = 1; i <= n; i++) {
            for(int j = m; j >= v[i]; j--) {
                f[j] = Math.max(f[j], f[j-v[i]] + w[i]);
            }
        }

        return f[m];
    }
}
