class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Every completely empty row can fit 2 families.
        int ans = 2 * n;

        HashMap<Integer, HashSet<Integer>> reserved = new HashMap<>();

        // Store reserved seats row-wise
        for (int[] seat : reservedSeats) {
            reserved
                .computeIfAbsent(seat[0], x -> new HashSet<>())
                .add(seat[1]);
        }

        // Only rows having reserved seats need to be checked
        for (int row : reserved.keySet()) {

            HashSet<Integer> seats = reserved.get(row);

            boolean left = true;   // 2,3,4,5
            boolean middle = true; // 4,5,6,7
            boolean right = true;  // 6,7,8,9

            for (int seat : seats) {

                if (seat >= 2 && seat <= 5) {
                    left = false;
                }

                if (seat >= 4 && seat <= 7) {
                    middle = false;
                }

                if (seat >= 6 && seat <= 9) {
                    right = false;
                }
            }

            int families = 0;

            if (left) {
                families++;
            }

            if (right) {
                families++;
            }

            // Middle can be used only if neither left nor right is available
            if (!left && !right && middle) {
                families++;
            }

            // This row was initially counted as 2
            ans -= 2;

            // Add the actual number possible in this reserved row
            ans += families;
        }

        return ans;
    }
}