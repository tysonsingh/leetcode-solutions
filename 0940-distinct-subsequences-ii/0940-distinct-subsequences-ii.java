class Solution {
    public int distinctSubseqII(String s) {

        long MOD = 1_000_000_007;

        long[] end = new long[26];
        long total = 0;

        for (char ch : s.toCharArray()) {

            int c = ch - 'a';

            long newSubseq = (total + 1) % MOD;

            total = (total + newSubseq - end[c] + MOD) % MOD;

            end[c] = newSubseq;
        }

        return (int) total;
    }
}