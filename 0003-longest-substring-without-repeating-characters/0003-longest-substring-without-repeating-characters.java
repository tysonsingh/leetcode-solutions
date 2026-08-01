class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() < 2) return s.length();

        int[] freq = new int[128];

        int left = 0;
        int len = s.length();
        int ans = 0;
        for(int right = 0; right < len; right++) {
            char cur_char = s.charAt(right);

            freq[cur_char]++;

            while(freq[cur_char] > 1) {
                char char_left = s.charAt(left);

                freq[char_left]--;

                left++;
            }

            ans = Math.max(ans, right - left + 1);

        }

        return ans;
    }
}