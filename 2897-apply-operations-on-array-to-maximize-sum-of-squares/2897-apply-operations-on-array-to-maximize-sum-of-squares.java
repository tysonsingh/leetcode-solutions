import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007L;
        int[] bitCount = new int[30];

        // Step 1: Count available 1-bits at each position (0 to 29)
        for (int num : nums) {
            for (int b = 0; b < 30; b++) {
                if ((num & (1 << b)) != 0) {
                    bitCount[b]++;
                }
            }
        }

        long ans = 0;

        // Step 2: Greedily construct k largest numbers
        for (int i = 0; i < k; i++) {
            long cur = 0;
            for (int b = 0; b < 30; b++) {
                if (bitCount[b] > 0) {
                    cur |= (1L << b);
                    bitCount[b]--;
                }
            }
            ans = (ans + (cur * cur) % MOD) % MOD;
        }

        return (int) ans;
    }
}