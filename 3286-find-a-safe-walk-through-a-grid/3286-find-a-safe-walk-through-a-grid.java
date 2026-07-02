class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        int startCost = grid.get(0).get(0);
        dist[0][0] = startCost;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[]{0, 0});

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int r = cur[0], c = cur[1];

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                int edgeCost = grid.get(nr).get(nc);
                int newDist = dist[r][c] + edgeCost;

                if (newDist < dist[nr][nc]) {
                    dist[nr][nc] = newDist;
                    if (edgeCost == 0) {
                        deque.offerFirst(new int[]{nr, nc});
                    } else {
                        deque.offerLast(new int[]{nr, nc});
                    }
                }
            }
        }

        return health > dist[m - 1][n - 1];
    }
}