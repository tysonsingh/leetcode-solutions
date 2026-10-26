
class Solution {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long total = floodFill(grid, i, j, m, n);
                    if (total % k == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private long floodFill(int[][] grid, int sr, int sc, int m, int n) {
        Deque<int[]> stack = new ArrayDeque<>();
        long sum = grid[sr][sc];
        grid[sr][sc] = 0;
        stack.push(new int[]{sr, sc});

        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            int r = cell[0];
            int c = cell[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + DR[d];
                int nc = c + DC[d];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                    sum += grid[nr][nc];
                    grid[nr][nc] = 0;
                    stack.push(new int[]{nr, nc});
                }
            }
        }
        return sum;
    }
}