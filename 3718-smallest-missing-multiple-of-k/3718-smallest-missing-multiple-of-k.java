class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> multiples = new HashSet<>();

        for(int n : nums) {
            multiples.add(n);
        }

        for(int i = 1; i <= nums.length + 1; i++) {
            if(!multiples.contains(k * i)) {
                return k * i;
            }
        }

        return -1;
    }
}