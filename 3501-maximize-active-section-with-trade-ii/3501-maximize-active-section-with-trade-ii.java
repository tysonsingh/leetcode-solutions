class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int[] lookup = new int[n];
        List<int[]> zeroRuns = new ArrayList<>(); // {start, length}
        int cnt1 = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroRuns.get(zeroRuns.size() - 1)[1]++;
                } else {
                    zeroRuns.add(new int[]{i, 1});
                }
            } else {
                cnt1++;
            }
            lookup[i] = zeroRuns.size() - 1;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) result.add(cnt1);

        int m = zeroRuns.size();
        if (m == 0) return result; // no '0' anywhere -> no trade ever possible

        int[][] idxs = zeroRuns.toArray(new int[0][]);
        int sz = m - 1;
        int[] arr = new int[Math.max(sz, 1)];
        for (int k = 0; k < sz; k++) {
            arr[k] = idxs[k][1] + idxs[k + 1][1];
        }
        SparseTable st = (sz > 0) ? new SparseTable(arr, sz) : null;

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            int left = lookup[l] + 1;
            int right = lookup[r] - (s.charAt(r) == '0' ? 1 : 0);
            int leftCnt = (lookup[l] != -1) ? idxs[lookup[l]][1] - (l - idxs[lookup[l]][0]) : -1;
            int rightCnt = (lookup[r] != -1) ? r - idxs[lookup[r]][0] + 1 : -1;

            int best = cnt1;

            if (st != null && left <= right - 1) {
                best = Math.max(best, cnt1 + st.query(left, right - 1));
            }
            if (s.charAt(l) == '0' && s.charAt(r) == '0' && lookup[l] + 1 == lookup[r]) {
                best = Math.max(best, cnt1 + leftCnt + rightCnt);
            }
            if (s.charAt(l) == '0' && lookup[l] + 1 <= right) {
                best = Math.max(best, cnt1 + leftCnt + idxs[lookup[l] + 1][1]);
            }
            if (s.charAt(r) == '0' && left <= lookup[r] - 1) {
                best = Math.max(best, cnt1 + rightCnt + idxs[lookup[r] - 1][1]);
            }

            result.set(i, best);
        }
        return result;
    }

    // Static (build-once) sparse table for range-maximum queries.
    static class SparseTable {
        int[][] table;
        int[] log;

        SparseTable(int[] arr, int n) {
            log = new int[n + 1];
            for (int i = 2; i <= n; i++) log[i] = log[i / 2] + 1;
            int levels = log[n] + 1;
            table = new int[levels][n];
            table[0] = arr.clone();
            for (int j = 1; j < levels; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    table[j][i] = Math.max(table[j - 1][i], table[j - 1][i + (1 << (j - 1))]);
                }
            }
        }

        int query(int l, int r) { // inclusive range max
            int j = log[r - l + 1];
            return Math.max(table[j][l], table[j][r - (1 << j) + 1]);
        }
    }
}