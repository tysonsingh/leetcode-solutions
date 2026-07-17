class Solution {

    public int[] gcdValues(int[] nums, long[] queries) {

        int MAX = 0;
        for (int x : nums) MAX = Math.max(MAX, x);

        int[] freq = new int[MAX + 1];

        for (int x : nums)
            freq[x]++;

        long[] divisible = new long[MAX + 1];

        for (int g = 1; g <= MAX; g++) {
            long cnt = 0;
            for (int j = g; j <= MAX; j += g)
                cnt += freq[j];
            divisible[g] = cnt;
        }

        long[] exact = new long[MAX + 1];

        for (int g = MAX; g >= 1; g--) {

            long pairs = divisible[g] * (divisible[g] - 1) / 2;

            for (int m = g + g; m <= MAX; m += g)
                pairs -= exact[m];

            exact[g] = pairs;
        }

        long[] prefix = new long[MAX + 1];

        for (int i = 1; i <= MAX; i++)
            prefix[i] = prefix[i - 1] + exact[i];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long target = queries[i] + 1;

            int lo = 1;
            int hi = MAX;

            while (lo < hi) {

                int mid = (lo + hi) / 2;

                if (prefix[mid] >= target)
                    hi = mid;
                else
                    lo = mid + 1;
            }

            ans[i] = lo;
        }

        return ans;
    }
}
















