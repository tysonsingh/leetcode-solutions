class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        final int MOD = 1_000_000_007;

        int[][] dp = new int[n][n];
        long[][] cnt = new long[n][n];

        for (int[] row : dp) Arrays.fill(row, -1);

        dp[n - 1][n - 1] = 0;
        cnt[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            char[] rowChars = board.get(i).toCharArray();
            for (int j = n - 1; j >= 0; j--) {
                if (i == n - 1 && j == n - 1) continue; // S already set
                char c = rowChars[j];
                if (c == 'X') continue; // blocked, stays -1

                int best = -1;
                long ways = 0;

                // check (i+1, j), (i, j+1), (i+1, j+1)
                for (int[] d : new int[][]{{1, 0}, {0, 1}, {1, 1}}) {
                    int ni = i + d[0], nj = j + d[1];
                    if (ni >= n || nj >= n) continue;
                    if (dp[ni][nj] == -1) continue;

                    if (dp[ni][nj] > best) {
                        best = dp[ni][nj];
                        ways = cnt[ni][nj];
                    } else if (dp[ni][nj] == best) {
                        ways = (ways + cnt[ni][nj]) % MOD;
                    }
                }

                if (best == -1) continue; // unreachable, dp[i][j] stays -1

                int cellValue = (c == 'E') ? 0 : (c - '0');
                dp[i][j] = best + cellValue;
                cnt[i][j] = ways;
            }
        }

        if (dp[0][0] == -1) return new int[]{0, 0};
        return new int[]{dp[0][0], (int) cnt[0][0]};
    }
}