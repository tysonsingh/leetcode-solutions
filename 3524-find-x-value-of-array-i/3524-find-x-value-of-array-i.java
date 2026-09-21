class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];

        // dp[r] = number of subarrays ending at the
        // previous index whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            int rem = num % k;

            long[] newDp = new long[k];

            // Start a new subarray with only nums[i]
            newDp[rem] = 1;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {

                int newRem = (int) ((long) r * rem % k);

                newDp[newRem] += dp[r];
            }

            // Add all subarrays ending at current index
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}