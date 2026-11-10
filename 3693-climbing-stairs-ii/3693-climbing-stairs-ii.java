class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        
        // Base case: starting at step 0 costs 0
        dp[0] = 0;
        
        for (int j = 1; j <= n; j++) {
            int minCost = Integer.MAX_VALUE;
            
            // Try jumping from all valid previous steps (i can be j-1, j-2, j-3)
            for (int i = Math.max(0, j - 3); i < j; i++) {
                int jumpCost = dp[i] + costs[j - 1] + (j - i) * (j - i);
                minCost = Math.min(minCost, jumpCost);
            }
            
            dp[j] = minCost;
        }
        
        return dp[n];
    }
}