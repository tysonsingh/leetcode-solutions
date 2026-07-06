class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });

        int count = 0;
        int maxEnd = Integer.MIN_VALUE;

        for (int[] interval : intervals) {
            int end = interval[1];
            if (end > maxEnd) {
                count++;
                maxEnd = end;
            }
            // else: covered by a previous interval, skip
        }

        return count;
    }
}