class Solution {
    static final long MOD = 1_000_000_007L;
    public int zigZagArrays(int n, int l, int r) {
        int m    = r - l + 1;   // number of valid values
        int size = 2 * m;       // state space: [up[0..m-1], down[0..m-1]]

        // Build transition matrix T
        // Row v       (0..m-1)  : newUp[v]   = sum of down[prev] for prev < v
        // Row m+v (m..2m-1) : newDown[v] = sum of up[prev]   for prev > v
        long[][] T = new long[size][size];
        for (int v = 0; v < m; v++) {
            for (int prev = 0; prev < v; prev++) {
                T[v][m + prev] = 1;     // up[v] += down[prev]
            }
            for (int prev = v + 1; prev < m; prev++) {
                T[m + v][prev] = 1;     // down[v] += up[prev]
            }
        }

            // Compute T^(n-1)
            long[][] Tn = matPow(T, n - 1, size);

            // Multiply T^(n-1) by initial vector [1,1,...,1]
            // = sum of each row in T^(n-1)
            long ans = 0;
            for (int i = 0; i < size; i++) {
                long rowSum = 0;
                for (int j = 0; j < size; j++) {
                    rowSum = (rowSum + Tn[i][j]) % MOD;
                }
                ans = (ans + rowSum) % MOD;
            }

            return (int) ans;
        }

        // Matrix multiplication: C = A × B mod MOD
        private long[][] matMul(long[][] A, long[][] B, int sz) {
            long[][] C = new long[sz][sz];
            for (int i = 0; i < sz; i++) {
                for (int k = 0; k < sz; k++) {
                    if (A[i][k] == 0) continue;     // skip zero rows (sparse optimization)
                    for (int j = 0; j < sz; j++) {
                        C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                    }
                }
            }
            return C;
    }

        // Binary matrix exponentiation: M^p mod MOD
        private long[][] matPow(long[][] M, int p, int sz) {
            // Start with identity matrix
            long[][] result = new long[sz][sz];
            for (int i = 0; i < sz; i++) result[i][i] = 1;

            while (p > 0) {
                if ((p & 1) == 1) result = matMul(result, M, sz);
                M = matMul(M, M, sz);
                p >>= 1;
            }
            return result;
    }
}