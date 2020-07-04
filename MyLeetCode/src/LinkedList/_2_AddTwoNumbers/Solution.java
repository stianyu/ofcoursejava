package LinkedList._2_AddTwoNumbers;

import java.util.List;

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
        return helper(l1, l2, 0);
    }

    private ListNode helper(ListNode l1, ListNode l2, int t) {
        if(l1 == null && l2 == null && t == 0) {
            return null;
        }
        if (l1 == null) {
            return helper(new ListNode(0), l2, t);
        }
        if (l2 == null) {
            return helper(l1, new ListNode(0), t);
        }

        int value = l1.val + l2.val + t;
        ListNode node = new ListNode(value % 10);
        node.next = helper(l1.next, l2.next, value / 10);
        return node;
    }

    public ListNode addTwoNumber2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode pre = dummyHead;
        int t = 0;
        while (l1 != null || l2 != null || t != 0) {
            if(l1 != null) {
                t += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                t += l2.val;
                l2 = l2.next;
            }
            pre.next = new ListNode(t % 10);
            pre = pre.next;
            t = t / 10;
        }
        return dummyHead;
    }

    public ListNode addTwoNumber3(ListNode l1, ListNode l2) {
        return add(l1, l2, 0);
    }

    private ListNode add(ListNode l1, ListNode l2, int t) {
        if (l1 == null && l2 == null && t == 0) {
            return null;
        }
        if (l1 == null) {
            l1 =  new ListNode(0);
        }
        if (l2 == null) {
            l2 = new ListNode(0);
        }
        int value = l1.val + l2.val + t;
        ListNode result = new ListNode(value % 10);
        result.next = add(l1.next, l2.next, value / 10);
        return result;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}