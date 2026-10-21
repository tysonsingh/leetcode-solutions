
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // 1. Check if already sorted
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) return 0;

        // 2. An unsorted string of length 2 cannot be sorted
        if (n == 2) return -1;

        // 3. Find minimum and maximum characters
        char minChar = s.charAt(0);
        char maxChar = s.charAt(0);

        for (int i = 1; i < n; i++) {
            char ch = s.charAt(i);

            if (ch < minChar) minChar = ch;
            if (ch > maxChar) maxChar = ch;
        }

        // 4. One operation is sufficient
        if (s.charAt(0) == minChar ||
            s.charAt(n - 1) == maxChar) {
            return 1;
        }

        // 5. Check the middle characters
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == minChar ||
                s.charAt(i) == maxChar) {
                return 2;
            }
        }

        // 6. Otherwise, three operations are sufficient
        return 3;
    }
}
