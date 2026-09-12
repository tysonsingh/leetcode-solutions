class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][];                       // [l, r, w, originalIndex]
        for (int i = 0; i < n; i++) {
            List<Integer> it = intervals.get(i);
            a[i] = new int[]{ it.get(0), it.get(1), it.get(2), i };
        }
        Arrays.sort(a, (p, q) -> Integer.compare(p[0], q[0]));

        int[] starts = new int[n];
        for (int i = 0; i < n; i++) starts[i] = a[i][0];

        int[] nxt = new int[n];
        for (int i = 0; i < n; i++) nxt[i] = upperBound(starts, a[i][1]);

        long[][] bestW = new long[n + 1][5];
        int[][][] bestIdx = new int[n + 1][5][];
        for (int k = 0; k <= 4; k++) bestIdx[n][k] = new int[0];

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k <= 4; k++) {
                long w = bestW[i + 1][k];               // option 1: skip
                int[] ids = bestIdx[i + 1][k];

                if (k > 0) {                            // option 2: take
                    long takeW = a[i][2] + bestW[nxt[i]][k - 1];
                    if (takeW >= w) {
                        int[] takeIds = insertSorted(bestIdx[nxt[i]][k - 1], a[i][3]);
                        if (takeW > w || lexSmaller(takeIds, ids)) {
                            w = takeW;
                            ids = takeIds;
                        }
                    }
                }
                bestW[i][k] = w;
                bestIdx[i][k] = ids;
            }
        }
        return bestIdx[0][4];
    }

    private int upperBound(int[] starts, int value) {   // first idx with starts[idx] > value
        int lo = 0, hi = starts.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (starts[mid] > value) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private int[] insertSorted(int[] arr, int val) {
        int[] res = new int[arr.length + 1];
        int p = 0;
        while (p < arr.length && arr[p] < val) { res[p] = arr[p]; p++; }
        res[p] = val;
        for (int j = p; j < arr.length; j++) res[j + 1] = arr[j];
        return res;
    }

    private boolean lexSmaller(int[] x, int[] y) {
        int m = Math.min(x.length, y.length);
        for (int i = 0; i < m; i++) {
            if (x[i] != y[i]) return x[i] < y[i];
        }
        return x.length < y.length;
    }
}