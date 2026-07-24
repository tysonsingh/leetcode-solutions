class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int LIMIT = 2048;               // 2^11 > 1500, XOR can't exceed this
        boolean[] pairXor = new boolean[LIMIT];
        int n = nums.length;

        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                pairXor[nums[i] ^ nums[j]] = true;

        boolean[] triplet = new boolean[LIMIT];
        for (int v = 0; v < LIMIT; v++) {
            if (!pairXor[v]) continue;
            for (int num : nums)
                triplet[v ^ num] = true;
        }

        int count = 0;
        for (boolean b : triplet)
            if (b) count++;
        return count;
    }
}