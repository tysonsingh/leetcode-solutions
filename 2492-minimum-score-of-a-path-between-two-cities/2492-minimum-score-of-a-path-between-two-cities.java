class Solution {
    private int[] parent, rank_;

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return;
        if (rank_[ra] < rank_[rb]) {
            parent[ra] = rb;
        } else if (rank_[ra] > rank_[rb]) {
            parent[rb] = ra;
        } else {
            parent[rb] = ra;
            rank_[ra]++;
        }
    }

    public int minScore(int n, int[][] roads) {
        parent = new int[n + 1];
        rank_ = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        for (int[] road : roads) {
            union(road[0], road[1]);
        }

        int root1 = find(1);
        int minDist = Integer.MAX_VALUE;

        for (int[] road : roads) {
            if (find(road[0]) == root1) {
                minDist = Math.min(minDist, road[2]);
            }
        }

        return minDist;
    }
}