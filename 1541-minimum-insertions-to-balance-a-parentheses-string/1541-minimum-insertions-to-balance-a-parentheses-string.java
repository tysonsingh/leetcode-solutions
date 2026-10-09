class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int rightNeeded = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If rightNeeded is odd, we have a lone ')' from before.
                // We must complete it with 1 insertion of ')' right now.
                if (rightNeeded % 2 != 0) {
                    insertions++;   // insert ')'
                    rightNeeded--;  // consumed
                }
                rightNeeded += 2;   // each '(' needs two ')'
            } else {
                rightNeeded--;
                // If rightNeeded drops below 0, we have an unexpected ')'
                if (rightNeeded < 0) {
                    insertions++;    // insert missing '(' before it
                    rightNeeded += 2;// '(' needs two ')', one is satisfied by this char
                }
            }
        }

        // Any remaining rightNeeded simply requires inserting that many ')'
        return insertions + rightNeeded;
    }
}