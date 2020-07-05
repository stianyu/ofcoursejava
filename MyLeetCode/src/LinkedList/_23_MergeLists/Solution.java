package LinkedList._23_MergeLists;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 23. 合并K个排序链表
 *      合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 示例:
 *      输入:
 *      [
 *          1->4->5,
 *          1->3->4,
 *          2->6
 *      ]
 *      输出: 1->1->2->3->4->4->5->6
 */
public class Solution {
    // 使用最小堆实现
    public ListNode mergeKLists(ListNode[] lists) {

        if(lists == null || lists.length == 0) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        Queue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for(ListNode list : lists) {
            if(list == null) {
                continue;
            }
            pq.add(list);
        }

        while(!pq.isEmpty()) {
            ListNode nextNode = pq.poll();
            cur.next = nextNode;
            cur = cur.next;
            if(nextNode.next != null) {
                pq.add(nextNode.next);
            }
        }

        return dummyHead.next;
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}