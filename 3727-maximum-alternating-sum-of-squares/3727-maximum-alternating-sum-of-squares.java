

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        // Step 1: Compute squares
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        // Step 2: Sort in ascending order
        Arrays.sort(squares);
        
        long score = 0;
        int mid = n / 2;
        
        // Step 3: Subtract smaller half, add larger half
        for (int i = 0; i < n; i++) {
            if (i < mid) {
                score -= squares[i];
            } else {
                score += squares[i];
            }
        }
        
        return score;
    }
}