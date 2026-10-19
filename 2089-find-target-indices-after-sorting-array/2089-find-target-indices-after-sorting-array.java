class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int countLess = 0;
        int countEqual = 0;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target) {
                countEqual++;
            }
            
            if(nums[i] < target) {
                countLess++;
            }
        }

        if(countEqual == 0) return new ArrayList<>();

        List<Integer> ans = new ArrayList<>();

        while(countEqual != 0) {
            ans.add(countLess);
            countLess++;
            countEqual--;
        }

        return ans;
    }
}