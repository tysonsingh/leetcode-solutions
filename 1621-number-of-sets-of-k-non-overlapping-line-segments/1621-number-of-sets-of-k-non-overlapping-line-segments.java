class Solution {
    static final long MOD = 1_000_000_007L;

    public int numberOfSets(int n, int k) {
        int maxVal = n + k;

        // Precompute factorials and inverse factorials
        long[] fact    = new long[maxVal + 1];
        long[] invFact = new long[maxVal + 1];

        fact[0] = 1;
        for (int i = 1; i <= maxVal; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[maxVal] = modPow(fact[maxVal], MOD - 2);
        for (int i = maxVal - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }

        // C(n + k - 1, 2k)
        int top = n + k - 1;
        int bot = 2 * k;

        return (int)(fact[top] * invFact[bot] % MOD * invFact[top - bot] % MOD);
    }

    // Modular exponentiation: base^exp mod MOD
    private long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }
}