package pdd;

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.util.Arrays;
import java.util.Scanner;
public class Main32 {
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

            int res = Integer.MAX_VALUE;
            if (T == 0) {
                System.out.println(0);
            } else {
                for (int i = 0; i < N; i++) {
                    if (y1[i] >= T) {
                        res = Math.min(res, x1[i]);
                    }
                }
                for (int i = 0; i < M; i++) {
                    if (y2[i] >= T) {
                        res = Math.min(res, x2[i]);
                    }
                }
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (y1[i] + y2[j] >= T) {
                            res = Math.min(res, x1[i] + x2[j]);
                        }
                    }
                }
                if (res == Integer.MAX_VALUE) {
                    System.out.println(-1);
                } else {
                    System.out.println(res);
                }
            }
        }
    }
}