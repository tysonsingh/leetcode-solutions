class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) ones1.add(new int[]{ i, j });
                if (img2[i][j] == 1) ones2.add(new int[]{ i, j });
            }
        }

        int size = 2 * n - 1;          // deltas range over [-(n-1), n-1]
        int[][] count = new int[size][size];
        int best = 0;

        for (int[] p : ones1) {
            for (int[] q : ones2) {
                int dx = q[0] - p[0] + n - 1;
                int dy = q[1] - p[1] + n - 1;
                best = Math.max(best, ++count[dx][dy]);
            }
        }
        return best;
    }
}