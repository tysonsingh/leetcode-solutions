class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char ch : s.toCharArray()) cnt[ch - 'a']++;

        // 1. Palindrome possible? If yes, the middle character is forced.
        int oddIdx = -1, oddCount = 0;
        for (int c = 0; c < 26; c++) {
            if (cnt[c] % 2 == 1) { oddIdx = c; oddCount++; }
        }
        if (oddCount > n % 2) return "";
        char mid = (n % 2 == 1) ? (char) ('a' + oddIdx) : 'a';

        int m = n / 2;
        int[] avail = new int[26];
        for (int c = 0; c < 26; c++) avail[c] = cnt[c] / 2;

        // 2. Match target's first half as far as our characters allow.
        int consumed = 0;
        while (consumed < m && avail[target.charAt(consumed) - 'a'] > 0) {
            avail[target.charAt(consumed) - 'a']--;
            consumed++;
        }

        // 3. Case B: half exactly equals target's first half; the tail decides.
        if (consumed == m) {
            String cand = build(target.substring(0, m), mid, n);
            if (cand.compareTo(target) > 0) return cand;
        }

        // 4. Case A: longest prefix match, then bump one position upward.
        for (int i = Math.min(consumed, m - 1); i >= 0; i--) {
            if (i < consumed) avail[target.charAt(i) - 'a']++;   // roll back
            for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                if (avail[c] > 0) {
                    avail[c]--;
                    StringBuilder half = new StringBuilder(target.substring(0, i));
                    half.append((char) ('a' + c));
                    for (int d = 0; d < 26; d++)
                        for (int k = 0; k < avail[d]; k++) half.append((char) ('a' + d));
                    return build(half.toString(), mid, n);
                }
            }
        }
        return "";
    }

    private String build(String half, char mid, int n) {
        StringBuilder sb = new StringBuilder(half);
        if (n % 2 == 1) sb.append(mid);
        sb.append(new StringBuilder(half).reverse());
        return sb.toString();
    }
}