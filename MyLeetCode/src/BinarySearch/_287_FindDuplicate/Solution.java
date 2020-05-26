package BinarySearch._287_FindDuplicate;

public class Solution {
    /**
     * 快慢指针法：（相当于链表找环的入口）
     *      时间O(n), 空间O(1)
     * 前提条件：nums的长度为n+1，因为环中的数字在1~n之间，因此num[i]作为索引在数组间检索值不会出现数组越界的问题。
     *         如果数组中出现重复数字，那么由数组的索引作为链表的下一个节点的指针，构成的链表将形成一个环，而环的入口即是数组重复的数字
     *      举个例子：nums = [2, 5, 9, 6, 9, 3, 8, 9, 7, 1]
     *              构造成链表就是：2->[9]->1->5->3->6->8->7->[9]，也就是在[9]处循环。
     *              使用快慢指针，快指针会在环内追上慢指针，不一定在环的入口处
     *              需要另一个for循环来找环的入口
      * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        // 类似于链表找环的思路
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 找环的入口
        slow = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * 二分查找法：
     *      时间O(nlogn), 空间O(1)
     * 我们不要考虑数组,只需要考虑 数字都在 1 到 n 之间
     *         示例 1:
     *         arr = [1,3,4,2,2] 此时数字在 1 — 5 之间
     *
     *         mid = (1 + 5) / 2 = 3 arr小于等于的3有4个(1,2,2,3)，1到3中肯定有重复的值
     *         mid = (1 + 3) / 2 = 2 arr小于等于的2有3个(1,2,2)，1到2中肯定有重复的值
     *         mid = (1 + 2) / 2 = 1 arr小于等于的1有1个(1)，2到2中肯定有重复的值
     *         所以重复的数是 2
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        int l = 1, r = nums.length;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,4,2,2};
        Solution solution = new Solution();
        System.out.println(solution.findDuplicate(nums));
    }
}
