class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        // suf[i] = longest suffix of word2 that is a subsequence of word1[i..n-1]
        int[] suf = new int[n + 1];
        suf[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            suf[i] = suf[i + 1];
            if (suf[i] < m && word1.charAt(i) == word2.charAt(m - 1 - suf[i])) {
                suf[i]++;
            }
        }

        int[] res = new int[m];
        int j = 0;
        boolean used = false;                       // mismatch budget spent?

        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                res[j++] = i;                       // free exact match
            } else if (!used && suf[i + 1] >= m - j - 1) {
                res[j++] = i;                       // spend the one mismatch here
                used = true;
            }
        }

        return j == m ? res : new int[0];
    }
}