package array._215_FindKthLargest;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * 215. 数组中的第K个最大元素
 *      在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1:
 *      输入: [3,2,1,5,6,4] 和 k = 2
 *      输出: 5
 * 示例 2:
 *      输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 *      输出: 4
 * 说明:
 *      你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class Solution {
    // 快排思想，加入了随机生成的l，防止顺序时退化成O(n^2)，加入了剪枝，不用全部排序完就能返回结果，因此时间复杂度为O(n)
    int res;
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0, nums.length - 1, k);
        // System.out.println(Arrays.toString(nums));
        return res;
    }

    private void quickSort(int[] nums, int l, int r, int k) {
        if(l > r) {
            return;
        }
        int p = partition(nums, l, r);
        // System.out.println(p);
        if(p == k - 1) {
            res = nums[p];
            return;
        }else if (p > k - 1) {
            quickSort(nums, l, p - 1, k);
        }else {
            quickSort(nums, p + 1, r, k);
        }
    }

    private int partition(int[] nums, int l, int r) {

        Random random = new Random();
        swap(nums, l, random.nextInt(r - l + 1) + l);

        int j = l;
        int temp = nums[l];
        for(int i = l + 1; i <= r; i++) {
            if(nums[i] > temp) {
                j++;
                swap(nums, j, i);
            }
        }
        swap(nums, l, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public int findKthLargest2(int[] nums, int k) {
        //使用最小堆简化版，去掉嵌套的if-else语句
        Queue<Integer> queue = new PriorityQueue<>();
        for(int num : nums) {
            queue.add(num);
            if(queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }
}
