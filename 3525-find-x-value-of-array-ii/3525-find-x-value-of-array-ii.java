class Solution {

    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
            prod = 1 % k;
        }
    }

    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;

        int n = nums.length;
        tree = new Node[4 * n];

        build(nums, 1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Point update
            update(1, 0, n - 1, index, value);

            // Query [start, n - 1]
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[i] = res.cnt[x];
        }

        return ans;
    }

    // ---------------- BUILD ----------------

    private void build(int[] nums, int node, int l, int r) {

        tree[node] = new Node(k);

        if (l == r) {

            int rem = nums[l] % k;

            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(nums, node * 2, l, mid);
        build(nums, node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ---------------- MERGE ----------------

    private Node merge(Node left, Node right) {

        Node res = new Node(k);

        /*
         * Product of complete segment
         */
        res.prod = (int) ((long) left.prod * right.prod % k);

        /*
         * Prefixes completely inside LEFT
         */
        for (int r = 0; r < k; r++) {
            res.cnt[r] += left.cnt[r];
        }

        /*
         * Prefixes that start somewhere in LEFT
         * and extend into RIGHT.
         *
         * Product =
         *
         * left.prod * rightPrefix
         */
        for (int r = 0; r < k; r++) {

            int newRem = (int) ((long) left.prod * r % k);

            res.cnt[newRem] += right.cnt[r];
        }

        return res;
    }

    // ---------------- UPDATE ----------------

    private void update(
            int node,
            int l,
            int r,
            int index,
            int value) {

        if (l == r) {

            int rem = value % k;

            tree[node] = new Node(k);

            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(
                tree[node * 2],
                tree[node * 2 + 1]
        );
    }

    // ---------------- QUERY ----------------

    private Node query(
            int node,
            int l,
            int r,
            int ql,
            int qr) {

        // Completely inside
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        // Completely in left
        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        // Completely in right
        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        // Crosses middle
        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}