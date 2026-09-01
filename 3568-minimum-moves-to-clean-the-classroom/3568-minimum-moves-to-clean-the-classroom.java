class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        char[][] g = new char[m][];
        for (int i = 0; i < m; i++) g[i] = classroom[i].toCharArray();

        // Step 1: locate S, assign a bit index to each litter cell
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) Arrays.fill(row, -1);
        int k = 0, sr = 0, sc = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 'L') litterId[i][j] = k++;
                else if (g[i][j] == 'S') { sr = i; sc = j; }
            }
        }
        int full = (1 << k) - 1;
        if (k == 0) return 0;

        // best[r][c][mask] = max energy seen in that situation
        int[][][] best = new int[m][n][1 << k];
        for (int[][] plane : best)
            for (int[] row : plane) Arrays.fill(row, -1);

        ArrayDeque<int[]> q = new ArrayDeque<>();
        best[sr][sc][0] = energy;
        q.add(new int[]{sr, sc, 0, energy});

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            moves++;                        // everything popped now is `moves-1` deep
            for (int t = 0; t < size; t++) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1], mask = cur[2], e = cur[3];

                if (e == 0) continue;       // out of energy and not on an R

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    if (g[nr][nc] == 'X') continue;

                    int ne = e - 1;
                    int nmask = mask;
                    if (litterId[nr][nc] >= 0) nmask |= 1 << litterId[nr][nc];
                    if (g[nr][nc] == 'R') ne = energy;   // refill on arrival

                    if (nmask == full) return moves;     // BFS ⇒ this is minimal

                    if (best[nr][nc][nmask] >= ne) continue;   // dominated
                    best[nr][nc][nmask] = ne;
                    q.add(new int[]{nr, nc, nmask, ne});
                }
            }
        }
        return -1;
    }
}