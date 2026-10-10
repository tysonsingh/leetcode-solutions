class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        long[] diff = new long[n];
        long maxDiff = 0;
        long total = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs((long) nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            total += diff[i];
        }

        if (total <= k) return 0;

        long low = 0, high = maxDiff;

        while (low < high) {
            long mid = low + (high - low) / 2;
            long needed = 0;

            for (long d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        long remainingOps = k;

        for (int i = 0; i < n; i++) {
            if (diff[i] > low) {
                remainingOps -= diff[i] - low;
                diff[i] = low;
            }
        }

        // Remaining operations reduce some differences from low to low - 1.
        for (int i = 0; i < n && remainingOps > 0; i++) {
            if (diff[i] == low && diff[i] > 0) {
                diff[i]--;
                remainingOps--;
            }
        }

        long ans = 0;
        for (long d : diff) {
            ans += d * d;
        }

        return ans;
    }
}