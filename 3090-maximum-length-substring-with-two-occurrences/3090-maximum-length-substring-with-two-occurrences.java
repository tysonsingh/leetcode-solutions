class Solution {
    public int maximumLengthSubstring(String s) {
        int maxLen = 0;

        int left = 0;
        int[] freq = new int[26];

        for(int right = 0; right < s.length(); right++) {
            char currChar = s.charAt(right);
            freq[currChar - 'a']++;

            while(freq[currChar - 'a'] > 2) {
                char mostLeft = s.charAt(left);
                freq[mostLeft - 'a']--;

                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}