class Solution {
    public int removeDuplicates(int[] nums) {
        int slow = 0; 
        
        // for(int fast = 1; fast < nums.length; fast++) {
        //     if(nums[slow] != nums[fast]) {
        //         slow++;
        //         nums[slow] = nums[fast];
        //     }
        // }

        // for(int fast = 0; fast < nums.length; fast ++) {
        //     if(slow == 0 || nums[fast] != nums[slow - 1] ) {
        //         nums[slow] = nums[fast];
        //         slow++;
        //     }
        // }
        // return slow ;

        //return slow + 1;

        
        //int slow = 0;
        for(int fast = 1; fast < nums.length; fast++) {
            if(nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        
        return slow + 1;
        
        
    }
}