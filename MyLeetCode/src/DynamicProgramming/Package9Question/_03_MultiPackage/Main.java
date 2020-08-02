package DynamicProgramming.Package9Question._03_MultiPackage;

/**
 * 多重背包问题I (OJ:AcWing.com)
 *      有 N 种物品和一个容量是 V 的背包。
 *      第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
 *      求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
 *      输出最大价值。
 * 输入格式
 *      第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 *      接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
 * 输出格式
 *      输出一个整数，表示最大价值。
 * 数据范围
 *      0<N,V≤100
 *      0<vi,wi,si≤100
 * 输入样例
 *      4 5
 *      1 2 3
 *      2 4 1
 *      3 4 3
 *      4 5 2
 * 输出样例：
 *      10
 */
/*
f[i] 总体积是i的情况下，最大价值是多少

for(int i = 0; i < n; i++) {
    for(int j = m; j >= v[i]; j--) {
        f[j] = Math.max(f[j], f[j - v[i]] + w[i], f[j - 2*v[i]] + 2 * w[i], ...);
    }
}

1. f[i] = 0;
f[m]

2. f[0] = 0, f[i] = -INF,i = !0;
max{f[0 ... m]}
*/

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] f = new int[110];
            for(int i = 0 ; i < n; i++) {
                int v = in.nextInt();
                int w = in.nextInt();
                int s = in.nextInt();
                for(int j = m; j >= 0; j--) {
                    for(int k = 1; k <= s && k * v <= j; k++) {
                        f[j] = Math.max(f[j], f[j - k * v] + k * w );
                    }
                }
            }
            System.out.println(f[m]);
        }
    }
}
