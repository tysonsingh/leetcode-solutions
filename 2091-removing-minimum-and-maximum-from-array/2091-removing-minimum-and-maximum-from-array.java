class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find min and max indices
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Put smaller index on the left
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // Case 1: Both from front
        int fromFront = right + 1;

        // Case 2: Both from back
        int fromBack = n - left;

        // Case 3: One from front, one from back
        int fromBoth = (left + 1) + (n - right);

        return Math.min(fromFront, Math.min(fromBack, fromBoth));
    }
}