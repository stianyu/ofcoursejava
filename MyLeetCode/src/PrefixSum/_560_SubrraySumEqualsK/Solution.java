package PrefixSum._560_SubrraySumEqualsK;

import java.util.HashMap;

/**
 * 560. 和为K的子数组（前缀和算法）
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 *      示例 1 :
 *          输入:nums = [1,1,1], k = 2
 *          输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *      说明 :
 *          数组的长度为 [1, 20,000]。
 *          数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 * 前缀和：一个数组的某项下标之前(包括此项元素)的所有数组元素的和。
 *      根据定义有：
 *          sum[i] = sum[i-1] + a[i]
 *          可以以O(1)的时间求出区间 [i, j] 的区间和为
 *          sum[i, j] = sum[i] - sum[j - 1]
 */
public class Solution {
    // 暴力穷举法 (brute force), 时间 O(n^2), 空间 O(n)
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 前缀和 prefixSum 是 nums[0, 1, ..., n-1] 的和
        int[] prefixSum = new int[n+1];
        for(int i = 0; i < n; i++) {
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }

        // （暴力法）穷举前缀和
        int count = 0;
        for(int r = 0; r < n; r++) {
            // 计算，有几个 l 能够使得 prefixSum[r+1] - prefixSum[l] = k。
            for(int l = 0; l <= r; l++) {
                // 根据前缀和得到区间 nums[l, r] 的和， 其中 0 <= l <= r <= nums.length - 1
                if(prefixSum[r+1] - prefixSum[l] == k) {
                    count++;
                }
            }
        }

        return count;
    }

    // 使用 HashMap 存放前缀和, 时间 O(n), 空间 O(n)
    public int subarraySum2(int[] nums, int k) {
        // HashMap： 前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer> prefixSum = new HashMap<>();

        prefixSum.put(0, 1);  // nums[i] 本身就等于k

        int count = 0;
        int sum0toR = 0;
        for(int i = 0; i < nums.length; i++) {
            sum0toR += nums[i];
            // 找前缀和 nums[0..j]
            int sum0toL = sum0toR - k;
            // 如果有这个前缀和 sum0toj，则直接更新
            if(prefixSum.containsKey(sum0toL)) {
                count += prefixSum.get(sum0toL);
            }
            // 加入当前 sum0toi 到前缀和中并记录次数
            prefixSum.put(sum0toR, prefixSum.getOrDefault(sum0toR, 0) + 1);
        }

        return count;
    }
}
