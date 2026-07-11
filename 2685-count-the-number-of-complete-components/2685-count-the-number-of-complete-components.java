import java.util.*;

class Solution {

    List<Integer>[] graph;
    boolean[] visited;
    int nodes;
    int edgeCount;

    public int countCompleteComponents(int n, int[][] edges) {

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        visited = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {

                nodes = 0;
                edgeCount = 0;

                dfs(i);

                edgeCount /= 2; // each edge counted twice

                if (edgeCount == nodes * (nodes - 1) / 2) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int node) {

        visited[node] = true;
        nodes++;
        edgeCount += graph[node].size();

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}