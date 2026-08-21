class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;

        // Precompute lcm for every subset once, outside the binary search.
        long[] lcmOf = new long[1 << n];
        lcmOf[0] = 1;
        for (int mask = 1; mask < (1 << n); mask++) {
            int low  = mask & -mask;              // lowest set bit
            int i    = Integer.numberOfTrailingZeros(low);
            long a   = lcmOf[mask ^ low];         // lcm of the rest
            long b   = coins[i];
            lcmOf[mask] = a / gcd(a, b) * b;      // divide first to stay small
        }

        long lo = 1, hi = (long) min(coins) * k;  // k-th value can't exceed this

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (count(mid, lcmOf, n) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private long count(long x, long[] lcmOf, int n) {
        long total = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            long q = x / lcmOf[mask];
            if ((Integer.bitCount(mask) & 1) == 1) {
                total += q;   // odd-sized subset -> add
            } else {
                total -= q;   // even-sized subset -> subtract
            }
        }
        return total;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int min(int[] a) {
        int m = a[0];
        for (int v : a) m = Math.min(m, v);
        return m;
    }
}