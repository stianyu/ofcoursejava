package DataStructure.Stack._155_MinStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        System.out.println(minStack.top());
        minStack.push(0);
        System.out.println(minStack.top());
        minStack.push(-3);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());

    }
}

class MinStack {
    Deque<Integer> stack1;
    Deque<Integer> stack2;
    /** initialize your data structure here. */
    public MinStack() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    public void push(int x) {
        stack1.push(x);
        if (!stack2.isEmpty() && stack2.peek() < x) {
            stack2.push(stack2.peek());
        } else {
            stack2.push(x);
        }
    }

    public void pop() {
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */