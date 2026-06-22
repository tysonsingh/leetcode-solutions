class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] charset = new int[26];

        for(char c : text.toCharArray()) {
            charset[c - 'a']++;
        }

        charset['l' - 'a'] = charset['l' - 'a'] / 2;
        charset['o' - 'a'] = charset['o' - 'a'] / 2;

        int maxBallon = Integer.MAX_VALUE;

        for(char c : "balon".toCharArray()) {
            maxBallon = Math.min( charset[c - 'a'] , maxBallon);
        }

        return maxBallon;
    }
}