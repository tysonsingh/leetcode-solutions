class Solution {
    public int maxVowels(String s, int k) {
        String vowels = "aeiou";
        int maxV = 0;
        int ans;
        for(int i = 0; i < k; i++) {
            char curr = s.charAt(i);
            if(vowels.contains(String.valueOf(curr))) maxV++;
        }

        ans = maxV > 0 ? maxV : 0;

        for(int i = k ; i < s.length(); i++) {
            //remove
            char prevChar = s.charAt(i-k);
            if ( vowels.contains(String.valueOf(prevChar)) ) maxV--;

            char curr = s.charAt(i);
            //Add 
            if(vowels.contains(String.valueOf(curr))) maxV++;

            ans = Math.max(maxV, ans);
        }

        return ans;
    }
}