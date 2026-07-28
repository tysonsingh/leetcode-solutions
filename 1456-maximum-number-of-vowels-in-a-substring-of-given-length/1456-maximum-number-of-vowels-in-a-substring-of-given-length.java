class Solution {
    public int maxVowels(String s, int k) {
        int maxV = 0;
        int ans;
        for(int i = 0; i < k; i++) {
            char curr = s.charAt(i);
            if(isVowel(curr)) maxV++;
        }

        ans = maxV;

        for(int i = k ; i < s.length(); i++) {
            //remove
            char prevChar = s.charAt(i-k);
            if (isVowel(prevChar)) maxV--;

            char curr = s.charAt(i);
            //Add 
            if(isVowel(curr)) maxV++;

            ans = Math.max(maxV, ans);
        }

        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' ||
               c == 'e' ||
               c == 'i' ||
               c == 'o' ||
               c == 'u';
    }
}