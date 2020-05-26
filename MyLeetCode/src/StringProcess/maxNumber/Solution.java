package StringProcess.maxNumber;

/**
 * class Solution {
 * public:
 *     int f(char ch) {
 *         return ch == 'a' || ch == 'e' || ch == 'o' || ch == 'i' || ch == 'u';
 *     }
 *     int maxVowels(string s, int k) {
 *         int n = s.length();
 *         int ans = 0, cur = 0;
 *         for (int i = 0; i < k; i++) cur += f(s[i]);
 *         ans = cur;
 *         for (int i = k; i < n; i++) {
 *             cur = cur + f(s[i]) - f(s[i - k]);
 *             ans = max(ans, cur);
 *         }
 *         return ans;
 *     }
 * };
 */
class Solution {
    public int maxVowels(String s, int k) {
        int num = 0;
        for(int i = 0 ; i < k; i++){
            if(isVowel(s.charAt(i))){
                num++;
            }
        }
        int res = num;
        for(int i = k ; i < s.length(); i++){
            if(isVowel(s.charAt(i))){
                num++;
            }
            if(isVowel(s.charAt(i - k))){
                num--;
            }
            res = Math.max(res, num);
        }
        return res;
    }

    private boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        String sentence = "abciiidef";
        int k = 3;
        Solution solution = new Solution();
        System.out.println(solution.maxVowels(sentence, k));
    }
}