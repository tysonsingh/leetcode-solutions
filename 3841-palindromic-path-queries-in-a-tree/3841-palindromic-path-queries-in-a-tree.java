import java.util.*;

class Solution {
    private int[] head, to, next;
    private int edgeCount = 0;
    private int[] tin, tout, depth;
    private int[][] up;
    private int timer = 0;
    private int LOG = 17; // 2^16 = 65536 > 5 * 10^4
    private int[] bit;
    private int n;

    private void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    // Range update (via prefix XOR trick) / Point update on Fenwick Tree
    private void updateBIT(int idx, int val) {
        for (; idx <= n; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private void updateRange(int l, int r, int val) {
        updateBIT(l, val);
        if (r + 1 <= n) {
            updateBIT(r + 1, val);
        }
    }

    // Point query on Fenwick Tree
    private int queryBIT(int idx) {
        int res = 0;
        for (; idx > 0; idx -= idx & -idx) {
            res ^= bit[idx];
        }
        return res;
    }

    // Iterative DFS to compute tin, tout, depth, and binary lifting table
    private void buildTree() {
        int[] stackU = new int[n];
        int[] stackP = new int[n];
        int[] stackEdge = new int[n];
        int top = 0;

        stackU[0] = 0;
        stackP[0] = 0;
        stackEdge[0] = head[0];
        tin[0] = ++timer;

        while (top >= 0) {
            int u = stackU[top];
            int p = stackP[top];
            int e = stackEdge[top];

            if (e == head[u]) {
                up[0][u] = p;
                for (int i = 1; i < LOG; i++) {
                    up[i][u] = up[i - 1][up[i - 1][u]];
                }
            }

            while (e != -1 && to[e] == p) {
                e = next[e];
            }

            if (e != -1) {
                stackEdge[top] = next[e];
                int v = to[e];
                depth[v] = depth[u] + 1;
                tin[v] = ++timer;
                top++;
                stackU[top] = v;
                stackP[top] = u;
                stackEdge[top] = head[v];
            } else {
                tout[u] = timer;
                top--;
            }
        }
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int i = 0; i < LOG; i++) {
            if (((diff >> i) & 1) != 0) {
                u = up[i][u];
            }
        }
        if (u == v) return u;
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[i][u] != up[i][v]) {
                u = up[i][u];
                v = up[i][v];
            }
        }
        return up[0][u];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        head = new int[n];
        Arrays.fill(head, -1);
        to = new int[2 * (n - 1)];
        next = new int[2 * (n - 1)];

        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
            addEdge(edge[1], edge[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[LOG][n];
        bit = new int[n + 2];

        buildTree();

        int[] currentMask = new int[n];
        for (int i = 0; i < n; i++) {
            currentMask[i] = 1 << (s.charAt(i) - 'a');
            updateRange(tin[i], tout[i], currentMask[i]);
        }

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            if (q.charAt(0) == 'u') { // "update u c"
                int firstSpace = q.indexOf(' ', 7);
                int u = Integer.parseInt(q.substring(7, firstSpace));
                char c = q.charAt(firstSpace + 1);
                
                int newMask = 1 << (c - 'a');
                int diff = currentMask[u] ^ newMask;
                if (diff != 0) {
                    currentMask[u] = newMask;
                    updateRange(tin[u], tout[u], diff);
                }
            } else { // "query u v"
                int firstSpace = q.indexOf(' ', 6);
                int u = Integer.parseInt(q.substring(6, firstSpace));
                int v = Integer.parseInt(q.substring(firstSpace + 1));

                int lca = getLCA(u, v);
                int pathMask = queryBIT(tin[u]) ^ queryBIT(tin[v]) ^ currentMask[lca];

                // At most one bit set
                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return result;
    }
}