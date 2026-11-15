class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0;
        long evenCount = 1; // Base case: prefix sum of 0 is even
        long currentSum = 0;
        long totalSubarrays = 0;
        int MOD = 1_000_000_007;

        for (int num : arr) {
            currentSum += num;

            if (currentSum % 2 == 1) {
                // Current prefix sum is odd, add the number of previous even prefix sums
                totalSubarrays = (totalSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // Current prefix sum is even, add the number of previous odd prefix sums
                totalSubarrays = (totalSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }

        return (int) totalSubarrays;
    }
}