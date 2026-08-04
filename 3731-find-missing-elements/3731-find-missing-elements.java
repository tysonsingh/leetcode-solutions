class Solution {
    public static List<Integer> ans;
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        ans = new ArrayList<>();
        for(int i = 0; i < nums.length - 1; i++) {
            if( nums[i + 1] - nums[i] != 1) {
                helper(nums, nums[i] , nums[i+1]);
            }
        }

        return ans;
    }

    public void helper(int[] nums, int start, int end) {
        for(int i = start + 1; i < end; i++) {
            ans.add(i);
        }
    }
}