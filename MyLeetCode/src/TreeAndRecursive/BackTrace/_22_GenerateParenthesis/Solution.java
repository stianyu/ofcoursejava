package TreeAndRecursive.BackTrace._22_GenerateParenthesis;

import java.util.LinkedList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 */
public class Solution {
    List<String> res;

    /**
     * 使用 String 来存储中间过程的节点
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        res = new LinkedList<>();
        if(n <= 0) {
            return res;
        }

        backTrack(n, 0, 0, "");

        return res;
    }

    private void backTrack(int n, int left, int right, String path) {
        if(left == n && right == n) {
            res.add(path);
            return;
        }

        // 始终要保证左括号比又括号添加得少
        if(left > right) {
            return;
        }

        if(left < n) {
            backTrack(n, left + 1, right, path + ")");
        }

        if(right < n) {
            backTrack(n, left, right + 1, path + "(");
        }
    }

    /**
     * 使用 StringBuilder 来存储中间过程的节点
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        res = new LinkedList<>();
        if(n <= 0) {
            return res;
        }

        StringBuilder sb = new StringBuilder();
        backTrack2(n, 0, 0, sb);

        return res;
    }

    private void backTrack2(int n, int left, int right, StringBuilder sb) {
        if(left == n && right == n) {
            res.add(new String(sb));
            return;
        }

        // 始终要保证左括号比又括号添加得少
        if(left > right) {
            return;
        }

        if(left < n) {
            sb.append(")");
            backTrack2(n, left + 1, right, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(right < n) {
            sb.append(")");
            backTrack2(n, left, right + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}


/*
我们会发现，这个代码有一点点神奇，它好像没有回溯的过程。但事实上，这个题回溯的过程并不明显。
这是因为在 Java 语言里：curStr + "(" 这个语法生成了新的字符串对象，因此，在递归的每一个结点，其实都生成了新的字符串对象。
因此，在递归终止的时候，我们直接将字符串添加到结果集中就好了。
感兴趣的朋友，不妨使用 StringBuilder 字符序列这个类进行搜索，这样的操作就很像我们之前的搜索，就一定会有「撤销选择」和「状态重置」的操作。
 */