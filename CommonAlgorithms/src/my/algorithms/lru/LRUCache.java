package my.algorithms.lru;

import sun.net.util.IPAddressUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 缓存淘汰算法：LRU
 *      当内存快满的时候优先删那些很久没用过的数据
 * 数据结构：
 *      哈希链表：双向链表 + 哈希表
 *      数据结构要满足：1. 快速查询一个 key 是否存在（哈希表 O(1)）
 *                    2. 快速删除、添加一个节点 (链表 O(1)) ----> 双向链表可以满足删除操作的时间复杂度为 O(1)
 */
public class LRUCache {

    // 双链表的节点
    class Node {
        public int key, val;
        public Node next, prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // 双向链表
    class DoubleList {
        private Node head, tail;  // 头尾虚节点
        private int size;  // 链表大小

        public DoubleList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // 在链表头部添加节点 newNode
        public void addFirst(Node newNode) {
            newNode.next = head.next;
            newNode.prev = head;
            head.next.prev = newNode;
            head.next = newNode;
            size++;
        }

        // 删除链表中的一个节点（ delNode 一定存在）
        public void remove(Node delNode) {
            delNode.prev.next = delNode.next;
            delNode.next.prev = delNode.prev;
            size--;
        }

        // 删除链表中最后一个节点并返回该节点
        public Node removeLast() {
            if (tail.prev == head) {
                return null;
            }
            Node last = tail.prev;
            remove(last);
            return last;
        }

        // 返回链表长度
        public int getSize() {
            return size;
        }
    }

    private HashMap<Integer, Node> map;
    private DoubleList cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key).val;
        put(key, val);
        return val;
    }

    public void put(int key, int val) {
        Node node = new Node(key, val);

        if (map.containsKey(key)) {
            cache.remove(map.get(key));
        } else {
            if (capacity == cache.getSize()) {
                Node last = cache.removeLast();
                map.remove(last.key);
            }
        }
        cache.addFirst(node);
        map.put(key, node);
    }
}

class LRUCache2{
    int capacity;
    LinkedHashMap<Integer, Integer> cache;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return cache.size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}
