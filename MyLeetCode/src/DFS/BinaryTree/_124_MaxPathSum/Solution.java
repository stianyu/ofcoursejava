package DFS.BinaryTree._124_MaxPathSum;

/**
 * 124. 二叉树中的最大路径和
 *      给定一个非空二叉树，返回其最大路径和。
 *      本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * 示例 1:
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出: 6
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 */
public class Solution {
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }
    // 返回以当前节点为根节点的Max Path（实际上是二叉树的后序遍历）
    private int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        res = Math.max(res, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    /*
        最大路径和：根据当前节点的角色，路径和可分为两种情况：
        一：以当前节点为根节点
            1.只有当前节点
            2.当前节点+左子树
            3.当前节点+右子书
            4.当前节点+左右子树
        这四种情况的最大值即为以当前节点为根的最大路径和
        此最大值要和已经保存的最大值比较，得到整个树的最大路径值

        二：当前节点作为父节点的一个子节点
            和父节点连接的话则需取【单端的最大值】
            1.只有当前节点
            2.当前节点+左子树
            3.当前节点+右子书
            这三种情况的最大值
     */
    private int dfs2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs2(root.left);
        int right = dfs2(root.right);
        int cur = root.val + Math.max(left, right);
        int res = Math.max(cur, Math.max(cur, root.val));
        return Math.max(root.val, Math.max(left, right));
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
