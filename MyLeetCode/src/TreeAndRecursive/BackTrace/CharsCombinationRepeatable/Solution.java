package TreeAndRecursive.BackTrace.CharsCombinationRepeatable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static List<List<Character>> res;
    static boolean[] visited;

    public static void main(String[] args) {
        String strs = "abcc";
        System.out.println(charsCombinationReteatable(strs));
    }

    private static List<List<Character>> charsCombinationReteatable(String strs) {
        if (strs == null || strs.length() == 0) {
            return null;
        }

        char[] chs = strs.toCharArray();
        res = new ArrayList<>();
        visited = new boolean[chs.length];
        Arrays.sort(chs);
        for (int i = 1; i < chs.length; i++) {
            dfs(chs,0, i, new ArrayList<>());
        }
        return res;
    }

    private static void dfs(char[] chs, int index, int len, List<Character> list) {
        if (list.size() == len) {
            res.add(new ArrayList<>(list));
        }
        for (int i = index; i < chs.length; i++) {
            if (!visited[i]) {
                if (i > 0 && chs[i] == chs[i-1] && !visited[i-1]) continue;
                visited[i] = true;
                list.add(chs[i]);
                dfs(chs, i+1, len,list);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}
