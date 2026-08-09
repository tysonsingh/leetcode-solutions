class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        Integer[][] memo = new Integer[n][n + 1];
        return dfs(0, 1, piles, suffix, memo);
    }

    private int dfs(int i, int m, int[] piles, int[] suffix, Integer[][] memo) {
        int n = piles.length;
        if (i >= n) return 0;
        if (i + 2 * m >= n) return suffix[i];        // can sweep the rest
        if (memo[i][m] != null) return memo[i][m];

        int best = 0;
        for (int x = 1; x <= 2 * m; x++) {
            best = Math.max(best, suffix[i] - dfs(i + x, Math.max(m, x), piles, suffix, memo));
        }
        return memo[i][m] = best;
    }
}