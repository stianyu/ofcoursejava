package LinkedList._86_PartitionLinkedList;

import LinkedList.common.ListNode;

/**
 * 86. 分隔链表
 *      给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *      你应当保留两个分区中每个节点的初始相对位置。
 * 示例:
 *      输入: head = 1->4->3->2->5->2, x = 3
 *      输出: 1->2->2->4->3->5
 */
public class Solution {
    // 和奇偶链表题328类似
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode dummyLeft = new ListNode(-1);
        ListNode dummyRight = new ListNode(-1);
        ListNode curLeft = dummyLeft;
        ListNode curRight = dummyRight;

        while (head != null) {
            if (head.val < x) {
                curLeft.next = head;
                curLeft = curLeft.next;
            } else {
                curRight.next = head;
                curRight = curRight.next;
            }
            head = head.next;
        }
        curRight.next = null;  // 把curRight.next 指向null，防止形成环形链表
        curLeft.next = dummyRight.next;

        return dummyLeft.next;

    }
}
