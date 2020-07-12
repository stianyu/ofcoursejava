package TreeAndRecursive.Tree._114_Flatten;

import TreeAndRecursive.common.TreeNode;

/**
 * 114. 二叉树展开为链表
 *      给定一个二叉树，原地将它展开为一个单链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 */
public class Solution {
    public void flatten(TreeNode root) {
        if(root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        TreeNode right = root.right;  // 暂存处理好的root的右子树
        root.right = root.left;  // 把整理好的左子树 作为以root根节点 的右子树
        root.left = null;  // root 为根的左子树应为null
        while(root.right != null) { // 遍历整理好的左子树到之前左子树的末尾节点
            root = root.right;
        }
        root.right = right; // 把末尾节点和暂存的处理好的右子树连起来
    }
}
