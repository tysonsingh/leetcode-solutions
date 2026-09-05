class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int len = nums.length;

        if(len == 1) return nums[0] - nums[0] <= k ? 0 : -1 ; 

        int[] max = new int[len];
        int[] min = new int[len];

        min[len - 1] = nums[len - 1];
        max[0] = nums[0];
        
        for(int i = len - 2; i >= 0; i-- ) {
            min[i] = Math.min(min[i+1],nums[i]);
        }

        for(int i = 1; i < len; i++ ) { 
            max[i] = Math.max(nums[i], max[i - 1]);
        }

        for(int index = 0; index < len; index++) {
            if(max[index] - min[index] <= k) {
                return index;
            }
        }

        return -1;
    }
}