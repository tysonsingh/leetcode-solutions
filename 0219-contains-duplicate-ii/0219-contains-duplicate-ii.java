class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> seen = new HashSet<>();

        int n = nums.length;
        int l = 0;
        int r = 0;

        while( r < n) {
            if( Math.abs(l-r) <= k ) {
                if(seen.contains(nums[r])) {
                    return true;
                }
                else {
                    seen.add(nums[r]);
                    r++;
                }
            }
            else {
                
                seen.remove(nums[l]);
                l++;
            }
        }

        return false;
    }
}