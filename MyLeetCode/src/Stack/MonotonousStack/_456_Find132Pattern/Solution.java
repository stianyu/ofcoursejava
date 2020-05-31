package Stack.MonotonousStack._456_Find132Pattern;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public boolean find132pattern(int[] nums) {
        if(nums == null || nums.length == 0) {
            return false;
        }
        // 使用单调减栈，从右向左遍历，若当前元素左侧有第一个大的元素，当前元素为次大元素
        Deque<Integer> stack = new LinkedList<>();
        int temp = Integer.MIN_VALUE;
        for(int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] < temp){
                return true;
            }
            while(!stack.isEmpty() && stack.peek() < nums[i]) {
                temp = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
