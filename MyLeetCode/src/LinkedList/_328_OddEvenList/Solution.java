package LinkedList._328_OddEvenList;

import LinkedList.common.ListNode;

/**
 * 328. 奇偶链表
 *      给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *      请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * 示例 1:
 *      输入: 1->2->3->4->5->NULL
 *      输出: 1->3->5->2->4->NULL
 * 示例 2:
 *      输入: 2->1->3->5->6->4->7->NULL
 *      输出: 2->3->6->7->1->5->4->NULL
 */
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode odd = head, even = head.next;
        ListNode evenHead = even;

        while(even != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;

        return head;  // 这里为什么可以返回 head? 意思是odd指向head，修改odd也会修改head吗，这是什么原理？
    }

    public ListNode oddEvenList2(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-1);
        ListNode cur = head;
        ListNode headOdd = odd, headEven = even;    // ?

        boolean isOdd = true;
        while(cur != null) {
            if(isOdd) {
                odd.next = cur;
                odd = odd.next;
            } else {
                even.next = cur;
                even = even.next;
            }
            cur = cur.next;
            isOdd = !isOdd;
        }
        even.next = null;
        odd.next = headEven.next;

        return headOdd.next;
    }
}
