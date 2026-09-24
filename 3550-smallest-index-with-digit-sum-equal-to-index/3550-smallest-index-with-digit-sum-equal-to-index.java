class Solution {
    public int smallestIndex(int[] nums) {

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < 10 && i == nums[i]) {
                return i;
            }
            else {
                int sum = 0;
                int num = nums[i];

                while( num != 0) {
                    sum += num % 10;
                    num /= 10;
                }

                if(sum == i) return i;
            }
        }

        return -1;

    }
}