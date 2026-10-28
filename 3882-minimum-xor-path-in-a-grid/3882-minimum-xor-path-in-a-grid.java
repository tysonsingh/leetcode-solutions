class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        final int MAX = 1024;                 // values <= 1023, so XOR hamesha [0, 1023] me rahega

        boolean[][] dp = new boolean[n][];    // dp[j] = current row ke column j pe reachable XORs

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                boolean[] cur = new boolean[MAX];

                if (i == 0 && j == 0) {
                    cur[v] = true;
                } else {
                    boolean[] up   = (i > 0) ? dp[j]     : null;
                    boolean[] left = (j > 0) ? dp[j - 1] : null;

                    for (int x = 0; x < MAX; x++) {
                        if ((up != null && up[x]) || (left != null && left[x])) {
                            cur[x ^ v] = true;
                        }
                    }
                }
                dp[j] = cur;
            }
        }

        for (int x = 0; x < MAX; x++) {
            if (dp[n - 1][x]) return x;
        }
        return -1;
    }
}