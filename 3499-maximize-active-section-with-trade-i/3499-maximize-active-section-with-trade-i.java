class Solution {
    public int maxActiveSectionsAfterTrade(String s) {

        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }

        List<Integer> zeroGroups = new ArrayList<>();

        int i = 0;
        while (i < s.length()) {

            if (s.charAt(i) == '1') {
                i++;
                continue;
            }

            int cnt = 0;
            while (i < s.length() && s.charAt(i) == '0') {
                cnt++;
                i++;
            }

            zeroGroups.add(cnt);
        }

        int bestMerge = 0;

        for (int j = 0; j + 1 < zeroGroups.size(); j++) {
            bestMerge = Math.max(bestMerge,
                    zeroGroups.get(j) + zeroGroups.get(j + 1));
        }

        return ones + bestMerge;
    }
}