class Solution {

    public int numDistinct(String s, String t) {

        int[][] dp = new int[s.length()][t.length()];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(s, t, 0, 0, dp);
    }

    public int helper(String s, String t, int i, int j, int[][] dp) {

        // t completely matched
        if (j == t.length()) {
            return 1;
        }

        // s khatam ho gaya, t abhi baaki hai
        if (i == s.length()) {
            return 0;
        }

        // Already calculated
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s.charAt(i) == t.charAt(j)) {

            // 1. s[i] ko use karo
            // 2. s[i] ko skip karo
            dp[i][j] = helper(s, t, i + 1, j + 1, dp)
                     + helper(s, t, i + 1, j, dp);

        } else {

            // Match nahi hua -> skip
            dp[i][j] = helper(s, t, i + 1, j, dp);
        }

        return dp[i][j];
    }
}

