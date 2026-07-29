import java.util.*;

class Solution {

    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        char middle = 0;
        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if ((freq[i] & 1) == 1)
                middle = (char) ('a' + i);
        }

        long total = countWays(half, halfLen);
        if (total < k)
            return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long cnt = countWays(half, halfLen - pos - 1);

                if (cnt >= k) {
                    left.append((char) ('a' + c));
                    break;
                }

                k -= cnt;
                half[c]++;
            }
        }

        StringBuilder ans = new StringBuilder(left);

        if (middle != 0)
            ans.append(middle);

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countWays(int[] half, int total) {

        long res = 1;

        int remaining = total;

        for (int x : half) {

            if (x == 0)
                continue;

            res *= comb(remaining, x);

            if (res > LIMIT)
                return LIMIT;

            remaining -= x;
        }

        return Math.min(res, LIMIT);
    }

    private long comb(int n, int r) {

        if (r > n)
            return 0;

        r = Math.min(r, n - r);

        long ans = 1;

        for (int i = 1; i <= r; i++) {

            ans = ans * (n - r + i) / i;

            if (ans > LIMIT)
                return LIMIT;
        }

        return ans;
    }
}