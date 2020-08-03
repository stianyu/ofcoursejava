package pdd;

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.util.Arrays;
import java.util.Scanner;
public class Main2 {
    int[][] value = {{1,2,3,4,5,6},{1,2,6,5,3,4},{1,2,4,3,6,5},{1,2,5,6,4,3},{6,5,3,4,1,2},{2,1,3,4,6,5},{5,6,3,4,2,1}};
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int N = in.nextInt();
            int[][] nums = new int[N][6];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 6; j++) {
                    nums[i][j] = in.nextInt();
                }
            }
            System.out.println(N - 1);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < 6; j++) {
//                    if (nums[i][j])
                }

            }
        }
    }
}