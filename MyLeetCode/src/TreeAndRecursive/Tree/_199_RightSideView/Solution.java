package TreeAndRecursive.Tree._199_RightSideView;

import TreeAndRecursive.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 *      给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 示例:
 *      输入: [1,2,3,null,5,null,4,6]
 *      输出: [1, 3, 4, 6]
 * 解释:
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *  /
 * 6               <---
 *
 */
public class Solution {
    List<Integer> res;

    // 使用层序遍历
    public List<Integer> rightSideView(TreeNode root) {
        res = new LinkedList<>();
        if(root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
                if(i == size - 1) {
                    res.add(node.val);
                }
            }
        }

        return res;
    }

    public List<Integer> rightSideViewDFS(TreeNode root) {
        res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        dfs(root, 0);

        return res;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
        if (level == res.size()) {
            res.add(root.val);
        }

        dfs(root.right, level + 1);
        dfs(root.left, level + 1);
    }

}