package LinkedList._92_ReverseBetween;

/**
 * 92. 反转链表 II
 *      反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 *      1 ≤ m ≤ n ≤ 链表长度。
 * 示例:
 *      输入: 1->2->3->4->5->NULL, m = 2, n = 4
 *      输出: 1->4->3->2->5->NULL
 */
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode sup = dummyHead;
        ListNode begin = head, end = head;

        for(int i = 1; i < m; i++) {
            begin = begin.next;
            sup = sup.next;
        }

        for(int i = 1; i <= n; i++) {
            end = end.next;
        }

        ListNode cur = begin;
        ListNode pre = null;
        while(cur != end) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        sup.next.next = cur;  // 注意首尾的连接（说明：sup.next 指向的是 2 这个节点，而 2这个节点的下一个节点就应该是旋转后的 cur）
        sup.next = pre;

        return dummyHead.next;
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}