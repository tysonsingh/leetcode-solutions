class Solution {
    public int maximumLength(int[] nums) {
        // Use Long keys to safely handle squaring beyond int range
        Map<Long, Integer> count = new HashMap<>();
        for (int x : nums) {
            count.merge((long) x, 1, Integer::sum);
        }

        // Special case: x = 1 (1^2 = 1, chain never advances)
        // Any odd number of 1s forms a valid palindrome
        int ans = 1;
        if (count.containsKey(1L)) {
            int c = count.get(1L);
            ans = (c % 2 == 1) ? c : c - 1; // largest odd number <= c
        }

        // For each base x > 1
        for (long x : count.keySet()) {
            if (x == 1L) continue;

            int length = 0;
            long cur = x;

            // Extend the chain while we have pairs (>= 2 occurrences)
            while (count.getOrDefault(cur, 0) >= 2) {
                length += 2;    // consume one pair from each side
                cur = cur * cur; // square: x -> x^2 -> x^4 -> ...
            }

            // Try to place cur as the single middle element
            int total;
            if (count.getOrDefault(cur, 0) >= 1) {
                total = length + 1;          // cur fits in the middle
            } else {
                total = Math.max(1, length - 1); // fall back: remove last pair, use prev as middle
            }

            ans = Math.max(ans, total);
        }

        return ans;
    }
}