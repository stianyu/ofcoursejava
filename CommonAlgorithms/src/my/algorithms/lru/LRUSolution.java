package my.algorithms.lru;

import java.util.HashMap;

public class LRUSolution {
    class Node{
        int key, value;
        Node next, prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    class DoubleLinkedList{
        Node head, tail;
        int size;

        public DoubleLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public int getSize() {
            return size;
        }

        public void addFirst(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
            size++;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public Node removeLast() {
            if(head.next == tail) {
                return null;
            }
            Node last = tail.prev;
            remove(last);
            return last;
        }
    }
    HashMap<Integer, Node> map;
    DoubleLinkedList dlk;
    int capacity;

    public LRUSolution(int capacity) {
        map = new HashMap<>();
        dlk = new DoubleLinkedList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        put(node.key, node.value);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {  // 更新
            Node node = map.get(key);
            dlk.remove(node);
            node.value = value;
            map.put(key, node);
            dlk.addFirst(node);
        } else {  // 添加
            Node node = new Node(key, value);
            if (capacity == dlk.getSize()) {
                Node delNode = dlk.removeLast();
                map.remove(delNode.key);
            }
            dlk.addFirst(node);
            map.put(key, node);
        }
    }

    public static void main(String[] args) {
        LRUSolution cache = new LRUSolution(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));;       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        System.out.println(cache.get(1));;       // 返回 -1 (未找到)
        System.out.println(cache.get(3));;       // 返回  3
        System.out.println(cache.get(4));;       // 返回  4
    }
}
