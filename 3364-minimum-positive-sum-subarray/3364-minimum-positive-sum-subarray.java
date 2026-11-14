import java.util.List;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        
        // Iterate through all possible starting points
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            // Iterate through all possible ending points
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;
                
                // Check if the subarray length is within [l, r]
                if (length >= l && length <= r) {
                    // We only care about positive sums greater than 0
                    if (currentSum > 0) {
                        minSum = Math.min(minSum, currentSum);
                    }
                }
                
                // If length exceeds r, we can stop extending this subarray
                if (length > r) {
                    break;
                }
            }
        }
        
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}