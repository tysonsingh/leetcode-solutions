class Solution {
    static final int MOD = 1_000_000_007;
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1; // number of distinct values

        // dp[v][0] = ways ending at value v with last move UP
        // dp[v][1] = ways ending at value v with last move DOWN
        long[] up   = new long[m];
        long[] down = new long[m];

        // Base case: length 1, no direction established yet
        Arrays.fill(up,   1);
        Arrays.fill(down, 1);

        // Build array for positions 2..n
        for (int i = 2; i <= n; i++) {
            // Prefix sum of down[] — to compute newUp[v] = sum(down[0..v-1])
            long[] prefDown = new long[m + 1];
            for (int v = 0; v < m; v++) {
                prefDown[v + 1] = (prefDown[v] + down[v]) % MOD;
            }

            // Prefix sum of up[] — to compute newDown[v] = sum(up[v+1..m-1])
            long[] prefUp = new long[m + 1];
            for (int v = 0; v < m; v++) {
                prefUp[v + 1] = (prefUp[v] + up[v]) % MOD;
            }
            long totalUp = prefUp[m];

            long[] newUp   = new long[m];
            long[] newDown = new long[m];

            for (int v = 0; v < m; v++) {
                // UP to v: previous was DOWN at some prev < v
                newUp[v] = prefDown[v]; // sum of down[0..v-1]

                // DOWN to v: previous was UP at some prev > v
                newDown[v] = (totalUp - prefUp[v + 1] + MOD) % MOD; // sum of up[v+1..m-1]
            }

            up   = newUp;
            down = newDown;
        }

        // Sum all valid endings
        long ans = 0;
        for (int v = 0; v < m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }

        return (int) ans;
    }
}