package LinkedList._82_DeleteDuplicatesII;

import LinkedList.common.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 *      给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 示例 1:
 *      输入: 1->2->3->3->4->4->5
 *      输出: 1->2->5
 * 示例 2:
 *      输入: 1->1->1->2->3
 *      输出: 2->3
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = head;

        int temp = Integer.MIN_VALUE;
        while(cur != null) {
            while(cur != null && cur.next != null && cur.val == cur.next.val) {
                pre.next = cur.next;
                temp = cur.next.val;
                cur = cur.next;
            }
            if(temp == cur.val) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }
}
