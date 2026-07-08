class Solution {

    static final long MOD = 1_000_000_007L;

    static class Node {
        int cnt;
        long sum;
        long val;

        Node(int cnt, long sum, long val) {
            this.cnt = cnt;
            this.sum = sum;
            this.val = val;
        }
    }

    Node[] tree;
    long[] pow10;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        tree = new Node[4 * n];
        build(1, 0, n - 1, s);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            Node res = query(1, 0, n - 1, queries[i][0], queries[i][1]);
            ans[i] = (int) ((res.val * res.sum) % MOD);
        }

        return ans;
    }

    private void build(int idx, int l, int r, String s) {
        if (l == r) {
            int d = s.charAt(l) - '0';
            if (d == 0) {
                tree[idx] = new Node(0, 0, 0);
            } else {
                tree[idx] = new Node(1, d, d);
            }
            return;
        }

        int mid = l + (r - l) / 2;

        build(idx * 2, l, mid, s);
        build(idx * 2 + 1, mid + 1, r, s);

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    private Node query(int idx, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[idx];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(idx * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(idx * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(idx * 2, l, mid, ql, qr);
        Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    private Node merge(Node left, Node right) {
        int cnt = left.cnt + right.cnt;
        long sum = left.sum + right.sum;
        long val = (left.val * pow10[right.cnt] + right.val) % MOD;

        return new Node(cnt, sum, val);
    }
}