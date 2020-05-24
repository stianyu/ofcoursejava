package StringProcess.Offer58;

class Solution {
    public String reverseWords(String s) {
        if(s == null) {
            return null;
        }
        if(s.length() == 0) {
            return "";
        }
        s = s.trim();
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = arr.length - 1; i >= 0; i--) {
//            System.out.println(arr[i]);
            if(arr[i].equals("")) {
                continue;
            }
            sb.append(arr[i]);
            if(i > 0) {
                sb.append(" ");
            }
        }
        return new String(sb);
    }

    public static void main(String[] args) {
        String s = "a good   example";
        Solution solution = new Solution();
        System.out.println(solution.reverseWords(s));
    }
}
