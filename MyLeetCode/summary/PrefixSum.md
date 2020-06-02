### 前缀和
#### 定义
前缀和：一个数组的某项下标之前(包括此项元素)的所有数组元素的和。

根据定义有：
    
    sum[i] = sum[i-1] + a[i]

可以以 O(1) 的时间求出区间 [i, j] 的区间和为
    
    sum[i, j] = sum[i] - sum[j - 1]

**一般前缀和配合 HashMap 使用**

#### LeetCode 560
> 560.和为K的子数组（前缀和算法）。
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

示例 1 :

输入:nums = [2, 1, 1, 1, 2], k = 3

输出: 3 , [2, 1], [1, 1, 1], [1, 2]。

#### 分析

暴力求解的思路是从前往后选定数组里的一个元素后，再遍历数组中该元素后面的元素，直到两个元素的和为k，若遍历完数组没有找到符合条件的，则进行下一个元素的遍历。

显然暴力法的时间复杂度是 O(n^2)。

```java
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
```

在暴力求解法中，判断一个连续子数组 [l, ..., r] (l <= r) 是否符合条件，就是计算

`prefixSum[r+1] - prefixSum[l] == k` 成立的个数。

暴力求解法里套了两层for 循环求解。考虑在第一层for循环遍历中，能直接判断当前元素和 K 的差值是多少，只要前面有前缀和为差值的的区间，就找到了符合条件的一组结果, 即计算

`sum0tol == sum0toR - k` 成立的个数

用一个 HashMap 存放已经遍历过的所有可能的前缀和，对应的键值是该前缀和出现的次数。

最后需要维护新的前缀和值在池子里的键值。

```java
public int subarraySum2(int[] nums, int k) {
    // HashMap： 前缀和 -> 该前缀和出现的次数
    HashMap<Integer, Integer> prefixSum = new HashMap<>();

    prefixSum.put(0, 1);  // nums[i] 本身就等于k

    int count = 0;
    int sum0toR = 0;
    for(int i = 0; i < nums.length; i++) {
        sum0toR += nums[i];
        // 找前缀和 nums[0..l]
        int sum0toL = sum0toR - k;
        // 如果有这个前缀和 sum0toL，则直接更新
        if(prefixSum.containsKey(sum0toL)) {
            count += prefixSum.get(sum0toL);
        }
        // 加入当前 sum0toR 到前缀和中并记录次数
        prefixSum.put(sum0toR, prefixSum.getOrDefault(sum0toR, 0) + 1);
    }

    return count;
}
```

#### LeetCode 974 
> 974.和可被 K 整除的子数组 （前缀和 + HashMap）。
> 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。

示例：

输入：A = [4,5,0,-2,-3,1], K = 5

输出：7

这道题的分析过程和 560 一样。在暴力解法中，原本是计算 
   
`(prefixSum(i) - prefixSum(j)) % K == 0 `的个数

相当于计算
 
`sum0toL = sum0toR % K` 的前缀和的个数

注意：负数求余数为 `(num % K + K) % K`， 不区分正负号

- 前缀和 + 暴力法
```java
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
```

- 前缀和 + HashMap 法
```java
// 前缀和 + HashMap法
public int subarraysDivByK2(int[] A, int K) {
    // 和 560 极其相似，利用前缀和 + HashMap 进行优化，使时间复杂度降到O(n)
    HashMap<Integer, Integer> prefixSum = new HashMap<>();

    // 这里要加这句是考虑只有单个数组值符合条件的这种情况，比如A[i] = 0/5/5n, 数目为1
    prefixSum.put(0, 1);

    int count = 0;
    int sum0toR = 0;
    for(int i = 0; i < A.length; i++) {
        sum0toR += A[i];
        // 原本是计算 (prefixSum(i) - prefixSum(j)) % K == 0 的个数，相当于计算 (prefixSum(i)) % K == prefixSum(j)的前缀和的个数
        int sum0toL = (sum0toR % K + K) % K; // 负数的余数为(num % K + K) % K 【不用区分正负数】
        if(prefixSum.containsKey(sum0toL)) {
            count += prefixSum.get(sum0toL);
        } else {
            count += 0; // 如果不包含就直接加0
        }
        prefixSum.put(sum0toL, prefixSum.getOrDefault(sum0toL, 0) + 1);
    }
    return count;
}
```