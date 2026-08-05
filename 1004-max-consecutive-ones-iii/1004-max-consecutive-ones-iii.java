class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int ans = 0;
        int maxCount = 0;

        for(int right = 0; right < nums.length; right++) {
            if(nums[right] == 0) maxCount++;

            while(maxCount > k) {
                if(nums[left] == 0) {
                    maxCount--;
                }
                left++;
            }
            ans = Math.max(ans, (right - left + 1));

        }

        return ans;
    }
}