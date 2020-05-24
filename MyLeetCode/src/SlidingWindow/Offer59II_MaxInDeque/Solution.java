package SlidingWindow.Offer59II_MaxInDeque;

import java.util.Deque;
import java.util.LinkedList;

class MaxQueue {
    private Deque<Integer> queue;
    private Deque<Integer> help;

    public MaxQueue() {
        queue = new LinkedList<>();
        help = new LinkedList<>();
    }

    public int max_value() {
        return queue.isEmpty() ? -1 : help.peek();
    }

    public void push_back(int value) {
        // 先正常入队，即新来的元素从队尾进入
        queue.offerLast(value);
        // 这里需要注意的是：如果新添加的元素比 help.peekLast() 大，
        // 则将 help.removeLast() 弹出。
        // 这是因为 help 队列中的队头保存的是当前 data 队列中最大的元素
        while(!help.isEmpty() && help.peekLast() < value) {
            help.pollLast();
        }
        help.offerLast(value);
    }

    public int pop_front() {
        if(queue.isEmpty()) {
            return -1;
        }
        // 先将 data 中队头的元素取出来
        int value = queue.pop();
        // 然后再看看这个队头元素是不是 help 中的队头元素，
        // 如果是的话，则需要弹出 help 中的队头元素，以便于更新当前队列的最大值
        if(help.peek() == value) {
            help.pop();
        }
        return value;
    }
}
