class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Odd number of cells can never produce balanced parentheses
        if ((m + n - 1) % 2 == 1) {
            return false;
        }

        // dp[i][j][balance]
        boolean[][][] dp = new boolean[m][n][m + n];

        // Starting cell must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Starting cell already initialized
                if (i == 0 && j == 0) {
                    continue;
                }

                int change = grid[i][j] == '(' ? 1 : -1;

                for (int balance = 0; balance < m + n; balance++) {

                    int previousBalance = balance - change;

                    if (previousBalance < 0 ||
                        previousBalance >= m + n) {
                        continue;
                    }

                    boolean reachable = false;

                    // From top
                    if (i > 0) {
                        reachable |= dp[i - 1][j][previousBalance];
                    }

                    // From left
                    if (j > 0) {
                        reachable |= dp[i][j - 1][previousBalance];
                    }

                    dp[i][j][balance] = reachable;
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}