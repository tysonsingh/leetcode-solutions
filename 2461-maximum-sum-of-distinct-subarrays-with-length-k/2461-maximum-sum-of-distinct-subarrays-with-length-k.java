class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> seen = new HashMap<>();

        long sum = 0;
        long ans = 0;

        for(int i = 0; i < k; i++) {
            sum += nums[i];
            seen.put(nums[i], seen.getOrDefault(nums[i],0)+1);
        }

        if(seen.size() == k) {
            ans = sum;
        }

        for(int i = k; i < nums.length; i++) {
            //remove outgoing
            sum -= nums[i-k];
            seen.put(nums[i-k], seen.get(nums[i-k])-1);

            if(seen.get(nums[i-k]) == 0) {
                seen.remove(nums[i-k]);
            }

            //Adding
            sum += nums[i];
            seen.put(nums[i], seen.getOrDefault(nums[i],0)+1);

            if(seen.size() == k) {
                ans = Math.max(ans,sum);
            }


        }

        return ans;


    }
}