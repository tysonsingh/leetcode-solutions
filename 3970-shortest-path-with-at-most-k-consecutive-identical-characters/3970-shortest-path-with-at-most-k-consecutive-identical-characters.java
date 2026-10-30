import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list: u -> list of [v, w]
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // Priority queue stores: [weight, u, count]
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        
        // minCost[u][count] stores the minimum weight to reach node u with a given streak count
        // count ranges from 1 to k, so size k + 1
        long[][] minCost = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(minCost[i], Long.MAX_VALUE);
        }

        // Start at node 0 with count 1 and weight 0
        pq.offer(new long[]{0, 0, 1});
        minCost[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (u == n - 1) {
                return (int) d;
            }

            if (d > minCost[u][count]) {
                continue;
            }

            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int w = neighbor[1];

                int nextCount = 1;
                if (labels.charAt(u) == labels.charAt(v)) {
                    nextCount = count + 1;
                }

                if (nextCount <= k) {
                    if (d + w < minCost[v][nextCount]) {
                        minCost[v][nextCount] = d + w;
                        pq.offer(new long[]{d + w, v, nextCount});
                    }
                }
            }
        }

        return -1;
    }
}