class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';

            if (first[ch] == -1) {
                first[ch] = i;
            }

            last[ch] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Create valid interval for each character
        for (int ch = 0; ch < 26; ch++) {

            if (first[ch] == -1) {
                continue;
            }

            int left = first[ch];
            int right = last[ch];

            boolean valid = true;

            for (int i = left; i <= right; i++) {

                int curr = s.charAt(i) - 'a';

                // This character appeared before our interval
                if (first[curr] < left) {
                    valid = false;
                    break;
                }

                // Expand interval
                right = Math.max(right, last[curr]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Greedy: choose interval with earliest ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> ans = new ArrayList<>();

        int prevEnd = -1;

        for (int[] interval : intervals) {

            int left = interval[0];
            int right = interval[1];

            if (left > prevEnd) {
                ans.add(s.substring(left, right + 1));
                prevEnd = right;
            }
        }

        return ans;
    }
}