class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + stoneValue[i];
        }

        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                int best = 0;

                for (int k = i; k < j; k++) {
                    int left  = pre[k + 1] - pre[i];
                    int right = pre[j + 1] - pre[k + 1];

                    if (left < right) {
                        best = Math.max(best, left + dp[i][k]);
                    } else if (left > right) {
                        best = Math.max(best, right + dp[k + 1][j]);
                    } else {
                        best = Math.max(best, Math.max(left + dp[i][k], right + dp[k + 1][j]));
                    }
                }

                dp[i][j] = best;
            }
        }

        return dp[0][n - 1];
    }
}