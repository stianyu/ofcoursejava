package PrefixSum._974SubarraysDividedByK;

import java.net.Socket;
import java.util.HashMap;

/**
 * 974. 和可被 K 整除的子数组 （前缀和 + HashMap）
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 *
 *      示例：
 *          输入：A = [4,5,0,-2,-3,1], K = 5
 *          输出：7
 *          解释：
 *              有 7 个子数组满足其元素之和可被 K = 5 整除：
 *              [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *      提示：
 *           1 <= A.length <= 30000
 *          -10000 <= A[i] <= 10000
 *          2 <= K <= 10000
 */
public class Solution {
    // 前缀和 + 暴力法
    public int subarraysDivByK(int[] A, int K) {
        int n = A.length;
        // 前缀和 prefixSum 是 nums[0, 1, ..., n-1] 的和
        int[] prefixSum = new int[n+1];
        for(int i = 0; i < n; i++) {
            prefixSum[i+1] = prefixSum[i] + A[i];
        }

        // （暴力法）穷举前缀和
        int count = 0;
        for(int r = 0; r < n; r++) {
            // 计算，有几个 l 能够使得 prefixSum[r+1] - prefixSum[l] = k。
            for(int l = 0; l <= r; l++) {
                // 根据前缀和得到区间 nums[l, r] 的和， 其中 0 <= l <= r <= nums.length - 1
                if((prefixSum[r+1] - prefixSum[l]) % K == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    // 前缀和 + HashMap法
    public int subarraysDivByK2(int[] A, int K) {
        // 和 560 极其相似，利用前缀和 + HashMap 进行优化，使时间复杂度降到O(n)
        HashMap<Integer, Integer> prefixSum = new HashMap<>();

        // 这里要加这句是考虑只有单个数组值符合条件的这种情况，比如A[i] = 0/5/5n, 数目为1
        prefixSum.put(0, 1);

        int count = 0;
        int sum0toi = 0;
        for(int i = 0; i < A.length; i++) {
            sum0toi += A[i];
            // 原本是计算 (prefixSum(i) - prefixSum(j)) % K == 0 的个数，相当于计算 (prefixSum(i)) % K == prefixSum(j)的前缀和的个数
            // int sum0toj = sum0toi % K; // 正数的余数
            int sum0toj = (sum0toi % K + K) % K; // 负数的余数为(num % K + K) % K 【不用区分正负数】
            if(prefixSum.containsKey(sum0toj)) {
                count += prefixSum.get(sum0toj);
            } else {
                count += 0; // 如果不包含就直接加0
            }
            prefixSum.put(sum0toj, prefixSum.getOrDefault(sum0toj, 0) + 1);
        }
        return count;
    }

    // 前缀和 + HashMap法的简化写法
    public int subarraysDivByK3(int[] A, int K) {
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1);

        int count = 0;
        int sum0toi = 0;
        for (int i = 0; i < A.length; i++) {
            sum0toi += A[i];
            int sum0toj = (sum0toi % K + K) % K;
            int preNum = prefixSum.getOrDefault(sum0toj, 0);
            count += preNum;
            prefixSum.put(sum0toj, preNum);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] A = new int[]{-1,2,9};
        int K = 2;
        Solution solution = new Solution();
        System.out.println(solution.subarraysDivByK2(A, K));
    }
}
