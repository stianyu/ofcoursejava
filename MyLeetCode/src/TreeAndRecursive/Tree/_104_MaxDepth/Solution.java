package TreeAndRecursive.Tree._104_MaxDepth;

import TreeAndRecursive.common.TreeNode;

/**
 * 104. 二叉树的最大深度
 *      给定一个二叉树，找出其最大深度。
 *      二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 */
public class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = maxDepth(root.left) + 1;
        int r = maxDepth(root.right) + 1;

        return Math.max(l, r);
    }

    public int maxDepth2(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }

        return Math.max(helper(root.left, depth + 1), helper(root.right, depth + 1));
    }
}
