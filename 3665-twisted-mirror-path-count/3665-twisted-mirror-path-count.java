class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long MOD = 1_000_000_007;

        // jump[i][j][0] stores the final landing (r, c) if we try to move right from (i, j)
        // jump[i][j][1] stores the final landing (r, c) if we try to move down from (i, j)
        int[][][] jump = new int[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Precompute for 'Moving Right' (direction 0)
                int r = i, c = j + 1;
                int dir = 0; // 0 means moving right, 1 means moving down
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        // entered while moving right -> turn down
                        dir = 1;
                        r = r + 1;
                    } else {
                        // entered while moving down -> turn right
                        dir = 0;
                        c = c + 1;
                    }
                }
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    jump[i][j][0] = -1; // Out of bounds indicator
                } else {
                    jump[i][j][0] = r * n + c; // Encoded coordinates
                }

                // Precompute for 'Moving Down' (direction 1)
                r = i + 1; c = j;
                dir = 1;
                while (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                    if (dir == 0) {
                        dir = 1;
                        r = r + 1;
                    } else {
                        dir = 0;
                        c = c + 1;
                    }
                }
                if (r < 0 || r >= m || c < 0 || c >= n) {
                    jump[i][j][1] = -1;
                } else {
                    jump[i][j][1] = r * n + c;
                }
            }
        }

        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                // Try moving right
                int targetR = jump[i][j][0] / n;
                int targetC = jump[i][j][0] % n;
                if (jump[i][j][0] != -1) {
                    dp[targetR][targetC] = (dp[targetR][targetC] + dp[i][j]) % MOD;
                }

                // Try moving down
                targetR = jump[i][j][1] / n;
                targetC = jump[i][j][1] % n;
                if (jump[i][j][1] != -1) {
                    dp[targetR][targetC] = (dp[targetR][targetC] + dp[i][j]) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}