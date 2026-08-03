class Solution {

    Integer[] dp;

    public String stoneGameIII(int[] stoneValue) {

        int diff = solve(stoneValue, 0);

        if (diff > 0)
            return "Alice";

        if (diff < 0)
            return "Bob";

        return "Tie";
    }

    private int solve(int[] stoneValue, int i) {

        if (i >= stoneValue.length)
            return 0;

        if (dp == null)
            dp = new Integer[stoneValue.length];

        if (dp[i] != null)
            return dp[i];

        int best = Integer.MIN_VALUE;
        int sum = 0;

        for (int k = 0; k < 3 && i + k < stoneValue.length; k++) {

            sum += stoneValue[i + k];

            best = Math.max(best, sum - solve(stoneValue, i + k + 1));
        }

        return dp[i] = best;
    }
}