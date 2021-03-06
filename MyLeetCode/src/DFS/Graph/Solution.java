package DFS.Graph;

import java.util.ArrayList;

/**
 * 1519. 子树中标签相同的节点数
 * 给你一棵树（即，一个连通的无环无向图），这棵树由编号从 0  到 n - 1 的 n 个节点组成，且恰好有 n - 1 条 edges 。树的根节点为节点 0 ，树上的每一个节点都有一个标签，也就是字符串 labels 中的一个小写字符（编号为 i 的 节点的标签就是 labels[i] ）
 *
 * 边数组 edges 以 edges[i] = [ai, bi] 的形式给出，该格式表示节点 ai 和 bi 之间存在一条边。
 *
 * 返回一个大小为 n 的数组，其中 ans[i] 表示第 i 个节点的子树中与节点 i 标签相同的节点数。
 *
 * 树 T 中的子树是由 T 中的某个节点及其所有后代节点组成的树。
 */
public class Solution {
    int[] res;
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        // 建邻接表
        ArrayList<Integer>[] table = new ArrayList[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            table[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            table[edges[i][0]].add(edges[i][1]);
            table[edges[i][1]].add(edges[i][0]);
        }

        res = new int[n];
        dfs(table, 0, visited, labels);
        return res;
    }

    // 返回结点u的所有子节点出现的字符频次
    private int[] dfs(ArrayList<Integer>[] table, int index, boolean[] visited, String labels) {
        visited[index] = true;
        int[] count = new int[26];
        ArrayList<Integer> list = table[index];

        for (int e : list) {
            if(!visited[e]) {
                int[] temp = dfs(table, e, visited, labels);
                for(int i = 0; i < 26; i++) {
                    count[i] += temp[i];
                }
            }
        }
        count[labels.charAt(index) - 'a']++;
        res[index] = count[labels.charAt(index) - 'a'];
        return count;
    }
}