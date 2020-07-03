package LinkedList._2_AddTwoNumbers;

/**
 * 2. 两数相加
 *      给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *      如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *      您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = helper(l1, l2, new ListNode(0));
        return node;
    }

    private ListNode helper(ListNode l1, ListNode l2, ListNode l3) {
        if (l1 == null && l2 != null) {
            return l2;
        }
        if (l1 != null && l2 == null) {
            return l1;
        }
        if(l1 == null && l2 == null) {
            return null;
        }
        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode head3 = l3;
        ListNode node = new ListNode(-1);
        int value = head1.val + head2.val + head3.val;
        if (value >= 10) {
            node.val = value - 10;
            l3.val = 1;
        }
        node.val = value;
        node.next = helper(l1.next, l2.next, l3);
        return node;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}