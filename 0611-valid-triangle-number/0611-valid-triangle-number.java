class Solution {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        int count = 0;

        if( n <= 2) return count;
        Arrays.sort(nums);
        for(int k = n - 1; k >= 2; k--) {

            int l = 0, r = k-1; 

            while( l < r) {
                if(nums[l] + nums[r] > nums[k]) {
                    count += r - l;
                    r--;
                }
                else {
                    l++;
                }
            }

        }

        return count;
    }
}