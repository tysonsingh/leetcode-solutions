class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer,Integer> seen = new HashMap<>();

        int left = 0;
        int maxLen = 0; 

        for(int right = 0; right < nums.length; right++) {
            seen.put(nums[right], seen.getOrDefault(nums[right],0)+1);

            while(seen.get(nums[right]) > k) {
                seen.put(nums[left], seen.get(nums[left])-1);
                if(seen.get(nums[left]) == 0 ) seen.remove(nums[left]);

                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);

        }

        return maxLen;
    }
}