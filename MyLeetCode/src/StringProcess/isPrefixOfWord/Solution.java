package StringProcess.isPrefixOfWord;

class Solution {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] arr = sentence.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].startsWith(searchWord)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String sentence = "hello from the other side\", searchWord = \"they";
        String searchWord = "they";
        Solution solution = new Solution();
        System.out.println(solution.isPrefixOfWord(sentence, searchWord));
    }
}