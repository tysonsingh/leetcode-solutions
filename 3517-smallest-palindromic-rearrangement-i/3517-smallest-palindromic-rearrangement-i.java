class Solution {
    public String smallestPalindrome(String s) {
        if(s.length() <= 1) return s;

        int[] alpha = new int[26];

        for(char c : s.toCharArray()) {
            alpha[ c - 'a']++;
        }

        int len = s.length();
        char middle = 0;
        StringBuilder left = new StringBuilder();
       
        for(int i = 0; i < 26; i++) {
            int count = alpha[i] / 2;
            
            while( count > 0) {
                left.append((char) ('a' + i));
                count = count - 1;
            }

            if (alpha[i] % 2 == 1) {
                middle = (char) ('a' + i);
            }

        }

        String firstHalf = left.toString();
        String secondHalf = new StringBuilder(firstHalf).reverse().toString();

        if (middle != 0) {
            return firstHalf + middle + secondHalf;
        }

        return firstHalf + secondHalf;
    }
}