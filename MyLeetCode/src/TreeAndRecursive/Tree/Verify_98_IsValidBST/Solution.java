package TreeAndRecursive.Tree.Verify_98_IsValidBST;

import TreeAndRecursive.common.TreeNode;
import javafx.scene.control.cell.CheckBoxListCell;

import java.time.chrono.MinguoDate;
import java.util.LinkedList;
import java.util.List;

/**
 * 98. 验证二叉搜索树
 *      给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *      假设一个二叉搜索树具有如下特征：
 *          节点的左子树只包含小于当前节点的数。
 *          节点的右子树只包含大于当前节点的数。
 *          所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class Solution {
    int last = Integer.MIN_VALUE;
    List<Integer> list;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (isValidBST(root.left)) {
            if (last < root.val) {
                last = root.val;
                return isValidBST(root.right);
            }
        }

        return false;
    }

    // 思路二：先中序遍历一遍树，然后再判断是否递增

    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        list = new LinkedList<>();
        inOrder(root);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }

        return true;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
}
