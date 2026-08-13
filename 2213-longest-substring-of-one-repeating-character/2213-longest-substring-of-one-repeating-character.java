class Solution {
    private int n;
    private char[] s;
    private int[] pref, suf, best, len;
    private char[] leftCh, rightCh;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.s = s.toCharArray();
        this.n = s.length();

        int size = 4 * n;
        pref = new int[size];
        suf  = new int[size];
        best = new int[size];
        len  = new int[size];
        leftCh  = new char[size];
        rightCh = new char[size];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int q = 0; q < k; q++) {
            update(1, 0, n - 1, queryIndices[q], queryCharacters.charAt(q));
            ans[q] = best[1];
        }
        return ans;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            pref[node] = suf[node] = best[node] = len[node] = 1;
            leftCh[node] = rightCh[node] = s[l];
            return;
        }
        int mid = (l + r) >>> 1;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        pull(node);
    }

    private void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            leftCh[node] = rightCh[node] = c;
            return;
        }
        int mid = (l + r) >>> 1;
        if (idx <= mid) update(2 * node, l, mid, idx, c);
        else            update(2 * node + 1, mid + 1, r, idx, c);
        pull(node);
    }

    private void pull(int node) {
        int L = 2 * node, R = 2 * node + 1;

        leftCh[node]  = leftCh[L];
        rightCh[node] = rightCh[R];
        len[node]     = len[L] + len[R];

        pref[node] = pref[L];
        suf[node]  = suf[R];
        best[node] = Math.max(best[L], best[R]);

        if (rightCh[L] == leftCh[R]) {                      // boundary joins
            best[node] = Math.max(best[node], suf[L] + pref[R]);
            if (pref[L] == len[L]) pref[node] = len[L] + pref[R];
            if (suf[R]  == len[R]) suf[node]  = len[R] + suf[L];
        }
    }
}