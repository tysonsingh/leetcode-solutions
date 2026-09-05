class Solution {
    List<List<Integer>> ans;
    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        List<Integer> small = new ArrayList<>();

        helper(nums, small, 0);

        return ans;
    }

    public void helper(int[] nums, List<Integer> small, int start) {
        ans.add(new ArrayList<>(small));
        for(int i = start; i < nums.length; i++) {
            small.add(nums[i]);
            helper(nums, small, i+1);
            small.remove(small.size() - 1);
        }
    }
}