class Solution {
    public int totalNumbers(int[] digits) {
        boolean[] used = new boolean[1000];
        int ans = 0;

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == 0) continue; // hundreds digit can't be 0

            for (int j = 0; j < digits.length; j++) {
                if (j == i) continue;

                for (int k = 0; k < digits.length; k++) {
                    if (k == i || k == j) continue;

                    if (digits[k] % 2 != 0) continue; // must be even

                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];

                    if (!used[num]) {
                        used[num] = true;
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}