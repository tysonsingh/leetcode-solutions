import java.util.*;

class Solution {
    public int missingInteger(int[] nums) {

        // Find the sum of the longest sequential prefix
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Put all numbers into a HashSet
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        // Find the smallest integer >= sum
        // which is not present in nums
        while (set.contains(sum)) {
            sum++;
        }

        return sum;
    }
}