import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        if (n <= 3) {
            return 0;
        }

        Arrays.sort(nums);

        // Case 1: Change two smallest elements -> max is nums[n - 1], min is nums[2]
        int changeTwoSmallest = nums[n - 1] - nums[2];

        // Case 2: Change two largest elements -> max is nums[n - 3], min is nums[0]
        int changeTwoLargest = nums[n - 3] - nums[0];

        // Case 3: Change smallest and largest -> max is nums[n - 2], min is nums[1]
        int changeOneSmallestOneLargest = nums[n - 2] - nums[1];

        return Math.min(changeTwoSmallest, Math.min(changeTwoLargest, changeOneSmallestOneLargest));
    }
}