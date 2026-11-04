class Solution {
    // Sliding window median tracker maintaining lower and upper halves
    static class MedianFinder {
        private final TreeSet<Integer> left;
        private final TreeSet<Integer> right;
        private final int[] nums;
        private long leftSum = 0;
        private long rightSum = 0;
        private final int targetLeftSize;

        public MedianFinder(int[] nums, int x) {
            this.nums = nums;
            this.targetLeftSize = (x + 1) / 2;

            // Sort by value first, then index to handle duplicate elements uniquely
            Comparator<Integer> cmp = (a, b) -> {
                if (nums[a] != nums[b]) {
                    return Integer.compare(nums[a], nums[b]);
                }
                return Integer.compare(a, b);
            };

            this.left = new TreeSet<>(cmp);
            this.right = new TreeSet<>(cmp);
        }

        public void add(int idx) {
            if (left.isEmpty() || nums[idx] <= nums[left.last()]) {
                left.add(idx);
                leftSum += nums[idx];
            } else {
                right.add(idx);
                rightSum += nums[idx];
            }
            rebalance();
        }

        public void remove(int idx) {
            if (left.contains(idx)) {
                left.remove(idx);
                leftSum -= nums[idx];
            } else {
                right.remove(idx);
                rightSum -= nums[idx];
            }
            rebalance();
        }

        private void rebalance() {
            while (left.size() > targetLeftSize) {
                int moved = left.pollLast();
                leftSum -= nums[moved];
                right.add(moved);
                rightSum += nums[moved];
            }
            while (left.size() < targetLeftSize && !right.isEmpty()) {
                int moved = right.pollFirst();
                rightSum -= nums[moved];
                left.add(moved);
                leftSum += nums[moved];
            }
        }

        public long getCost() {
            long median = nums[left.last()];
            long leftCost = (long) left.size() * median - leftSum;
            long rightCost = rightSum - (long) right.size() * median;
            return leftCost + rightCost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int m = n - x + 1;
        long[] cost = new long[m];

        MedianFinder mf = new MedianFinder(nums, x);

        // Precompute cost for all windows of size x
        for (int i = 0; i < n; i++) {
            mf.add(i);
            if (i >= x) {
                mf.remove(i - x);
            }
            if (i >= x - 1) {
                cost[i - x + 1] = mf.getCost();
            }
        }

        // dp[j][i]: min cost selecting j non-overlapping windows within first i elements
        // Optimized space to O(n) per window count j
        long INF = Long.MAX_VALUE / 2;
        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);
            for (int i = j * x; i <= n; i++) {
                // Option 1: Do not end a window at i - 1
                curr[i] = curr[i - 1];

                // Option 2: Place a window ending at i - 1 (starts at i - x)
                long prevCost = prev[i - x];
                if (prevCost != INF) {
                    curr[i] = Math.min(curr[i], prevCost + cost[i - x]);
                }
            }
            System.arraycopy(curr, 0, prev, 0, n + 1);
        }

        return prev[n];
    }
}