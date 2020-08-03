package pdd;

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int k = in.nextInt();
            int n = in.nextInt();
            int sum = 0;
            int count = 0;
            for (int i = 0; i < n; i++) {
                sum += in.nextInt();
                if(sum == k) {
                    System.out.println("paradox");
                    break;
                } else if (sum > k){
                    k = sum - k;
                    if (i != n -1) {
                        sum = 0;
                    }
                    count++;
                }
            }
            if (sum < k) {
                System.out.println( (k - sum) + " " + count);
            } else if (sum > k) {
                System.out.println(k + " " + count);
            }
        }
    }
}
