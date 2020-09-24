package my.algorithms.circyeQueue;

public class circyeQueue {
    private int[] nums;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public circyeQueue(int capacity) {
        this.nums = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
        this.capacity = capacity;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == capacity;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        nums[rear] = value;
        rear = (rear + 1) % capacity;
        count++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        count--;
        return true;
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return nums[(front + count - 1) % capacity];
    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return nums[front];
    }
}
