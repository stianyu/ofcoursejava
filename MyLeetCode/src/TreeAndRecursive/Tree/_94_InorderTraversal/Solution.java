package TreeAndRecursive.Tree._94_InorderTraversal;

import TreeAndRecursive.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 *      给定一个二叉树，返回它的中序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {  // 递归写法
        if(root == null) {
            return res;
        }
        inorderTraversal(root.left);
        res.add(root.val);
        inorderTraversal(root.right);
        return res;
    }

    public List<Integer> inorderTraversalRer(TreeNode root) {  // 迭代写法
        List<Integer> res = new ArrayList<>();

        if(root == null) {
            return res;
        }

        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }

        return res;
    }

}
