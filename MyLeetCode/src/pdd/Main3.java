package pdd;

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.util.Arrays;
import java.util.Scanner;
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int N = in.nextInt();
            int M = in.nextInt();
            int T = in.nextInt();
            int[] x1 = new int[N];
            int[] y1 = new int[N];
            int[] x2 = new int[M];
            int[] y2 = new int[M];
            for (int i = 0; i < N; i++) {
                x1[i] = in.nextInt();
                y1[i] = in.nextInt();
            }
            for (int i = 0; i < M; i++) {
                x2[i] = in.nextInt();
                y2[i] = in.nextInt();
            }

            int[] dp = new int[T+1];
            Arrays.fill(dp, T+1);
            dp[0] = 0;
            for (int j = T; j >= 0; j--) {
                for (int i = 0; i < N; i++) {
                    if (j >= y1[i])
                        dp[j] = Math.min(dp[j], dp[j - y1[i]] + x1[i]);
                }
                for (int i = 0; i < M; i++) {
                    if (j >= y2[i])
                        dp[j] = Math.min(dp[j], dp[j - y2[i]] + x2[i]);
                }
            }

            if (dp[T] == T+1) {
                System.out.println(-1);
            } else {
                System.out.println(dp[T]);
            }
        }
    }
}