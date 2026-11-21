import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }

        int[] finalParent = new int[n];
        Arrays.fill(finalParent, -1);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        assignFinalParents(0, origTree, s, parent, finalParent, lastSeen);

        List<Integer>[] finalTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            finalTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            finalTree[finalParent[i]].add(i);
        }

        int[] answer = new int[n];
        computeSubtreeSize(0, finalTree, answer);
        return answer;
    }

    private void assignFinalParents(
            int u,
            List<Integer>[] origTree,
            String s,
            int[] parent,
            int[] finalParent,
            int[] lastSeen) {
        int ch = s.charAt(u) - 'a';
        if (u > 0) {
            finalParent[u] = (lastSeen[ch] != -1) ? lastSeen[ch] : parent[u];
        }

        int prev = lastSeen[ch];
        lastSeen[ch] = u;

        for (int v : origTree[u]) {
            assignFinalParents(v, origTree, s, parent, finalParent, lastSeen);
        }

        lastSeen[ch] = prev;
    }

    private int computeSubtreeSize(int u, List<Integer>[] finalTree, int[] answer) {
        int size = 1;
        for (int v : finalTree[u]) {
            size += computeSubtreeSize(v, finalTree, answer);
        }
        answer[u] = size;
        return size;
    }
}