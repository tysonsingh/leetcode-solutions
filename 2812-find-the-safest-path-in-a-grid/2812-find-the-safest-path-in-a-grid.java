class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] safeness = new int[n][n];
        for (int[] row : safeness) Arrays.fill(row, -1);

        // Step 1: Multi-source BFS from all thieves
        Queue<int[]> bfsQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    safeness[i][j] = 0;
                    bfsQueue.offer(new int[]{i, j});
                }
            }
        }

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        while (!bfsQueue.isEmpty()) {
            int[] cell = bfsQueue.poll();
            int r = cell[0], c = cell[1];
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && safeness[nr][nc] == -1) {
                    safeness[nr][nc] = safeness[r][c] + 1;
                    bfsQueue.offer(new int[]{nr, nc});
                }
            }
        }

        // Step 2: Max-heap Dijkstra-style widest path
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // int[] = {currentPathSafeness, row, col}
        maxHeap.offer(new int[]{safeness[0][0], 0, 0});

        boolean[][] visited = new boolean[n][n];

        while (!maxHeap.isEmpty()) {
            int[] top = maxHeap.poll();
            int pathSafeness = top[0], r = top[1], c = top[2];

            if (visited[r][c]) continue;
            visited[r][c] = true;

            if (r == n - 1 && c == n - 1) {
                return pathSafeness;
            }

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    int newSafeness = Math.min(pathSafeness, safeness[nr][nc]);
                    maxHeap.offer(new int[]{newSafeness, nr, nc});
                }
            }
        }

        return 0; // unreachable given constraints (n >= 1, (0,0) always exists)
    }
}