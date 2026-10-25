
class Solution {
    int rows, cols;

    public int closedIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean dfs(int[][] grid, int r, int c) {
        // Is this cell inside the boundary?
        boolean closed = r > 0 && r < rows - 1
                      && c > 0 && c < cols - 1;

        // Mark as visited
        grid[r][c] = 1;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if (nr >= 0 && nr < rows &&
                nc >= 0 && nc < cols &&
                grid[nr][nc] == 0) {

                boolean neighborClosed = dfs(grid, nr, nc);

                if (!neighborClosed) {
                    closed = false;
                }
            }
        }

        return closed;
    }
}
