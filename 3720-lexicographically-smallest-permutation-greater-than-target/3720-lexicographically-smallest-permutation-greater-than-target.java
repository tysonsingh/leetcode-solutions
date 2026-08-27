class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {

            int curr = target.charAt(i) - 'a';

            // Try to keep the prefix equal to target
            if (freq[curr] > 0) {
                freq[curr]--;
                ans.append(target.charAt(i));
                continue;
            }

            // Can't keep equal.
            // Try the smallest character greater than target[i].
            for (int ch = curr + 1; ch < 26; ch++) {

                if (freq[ch] > 0) {
                    freq[ch]--;

                    ans.append((char) ('a' + ch));

                    // Once we are greater, remaining chars
                    // should be placed in smallest order.
                    for (int j = 0; j < 26; j++) {
                        while (freq[j] > 0) {
                            ans.append((char) ('a' + j));
                            freq[j]--;
                        }
                    }

                    return ans.toString();
                }
            }

            /*
             * No character >= target[i] is possible here.
             * We need to go back and change an earlier position.
             */
            while (ans.length() > 0) {

                int pos = ans.length() - 1;

                char oldChar = ans.charAt(pos);
                freq[oldChar - 'a']++;
                ans.deleteCharAt(pos);

                int old = target.charAt(pos) - 'a';

                // Try to make this position slightly bigger
                for (int ch = old + 1; ch < 26; ch++) {

                    if (freq[ch] > 0) {
                        freq[ch]--;

                        ans.append((char) ('a' + ch));

                        // Fill remaining characters smallest first
                        for (int j = 0; j < 26; j++) {
                            while (freq[j] > 0) {
                                ans.append((char) ('a' + j));
                                freq[j]--;
                            }
                        }

                        return ans.toString();
                    }
                }
            }

            return "";
        }

        // s itself is exactly equal to target
        // Need to find a larger permutation by backtracking.
        while (ans.length() > 0) {

            int pos = ans.length() - 1;

            char oldChar = ans.charAt(pos);
            freq[oldChar - 'a']++;
            ans.deleteCharAt(pos);

            int old = target.charAt(pos) - 'a';

            for (int ch = old + 1; ch < 26; ch++) {

                if (freq[ch] > 0) {
                    freq[ch]--;

                    ans.append((char) ('a' + ch));

                    for (int j = 0; j < 26; j++) {
                        while (freq[j] > 0) {
                            ans.append((char) ('a' + j));
                            freq[j]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}