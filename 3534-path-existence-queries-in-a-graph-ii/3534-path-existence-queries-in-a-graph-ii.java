import java.util.Arrays;

class Solution {

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            value[i] = arr[i][0];
            pos[arr[i][1]] = i;
        }

        // Connected components
        int[] comp = new int[n];
        int compId = 0;
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (value[i] - value[i - 1] > maxDiff) {
                compId++;
            }
            comp[i] = compId;
        }

        // Furthest reachable to the right
        int[] nextRight = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            while (r + 1 < n && value[r + 1] - value[i] <= maxDiff) {
                r++;
            }
            nextRight[i] = r;
        }

        // Furthest reachable to the left
        int[] nextLeft = new int[n];
        int l = 0;
        for (int i = 0; i < n; i++) {
            while (value[i] - value[l] > maxDiff) {
                l++;
            }
            nextLeft[i] = l;
        }

        int LOG = 18;

        int[][] upRight = new int[LOG][n];
        int[][] upLeft = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            upRight[0][i] = nextRight[i];
            upLeft[0][i] = nextLeft[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                upRight[k][i] = upRight[k - 1][upRight[k - 1][i]];
                upLeft[k][i] = upLeft[k - 1][upLeft[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int u = pos[queries[i][0]];
            int v = pos[queries[i][1]];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            if (comp[u] != comp[v]) {
                ans[i] = -1;
                continue;
            }

            if (u < v) {
                ans[i] = solveRight(u, v, upRight, LOG);
            } else {
                ans[i] = solveLeft(u, v, upLeft, LOG);
            }
        }

        return ans;
    }

    private int solveRight(int from, int target, int[][] up, int LOG) {

        int cur = from;
        int steps = 0;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][cur] < target) {
                cur = up[k][cur];
                steps += 1 << k;
            }
        }

        return steps + 1;
    }

    private int solveLeft(int from, int target, int[][] up, int LOG) {

        int cur = from;
        int steps = 0;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][cur] > target) {
                cur = up[k][cur];
                steps += 1 << k;
            }
        }

        return steps + 1;
    }
}