package TreeAndRecursive.Tree._Offer54_kthLargest;

import TreeAndRecursive.common.TreeNode;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 *      给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 * 限制：
 *      1 ≤ k ≤ 二叉搜索树元素个数
 */
public class Solution {
    int res;
    int k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;

        inOrder(root);

        return res;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.right);
        if (k == 1) {
            res = root.val;
            k --;
            return;
        }
        k--;
        inOrder(root.left);
    }
}
