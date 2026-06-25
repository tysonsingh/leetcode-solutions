class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        // Build prefix sum of +1/-1 transformed array
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        // Count pairs (l, r) where prefix[l] < prefix[r]
        // => subarray [l..r-1] has positive sum => target is majority
        int count = 0;
        for (int l = 0; l <= n; l++) {
            for (int r = l + 1; r <= n; r++) {
                if (prefix[l] < prefix[r]) {
                    count++;
                }
            }
        }

        return count;
    }
}