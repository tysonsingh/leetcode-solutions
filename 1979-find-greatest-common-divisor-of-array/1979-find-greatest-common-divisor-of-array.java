class Solution {
    public int findGCD(int[] nums) {
        int smallest = Integer.MAX_VALUE;
        int largest = Integer.MIN_VALUE;

        if(nums.length == 2) return helpGCD(nums[0],nums[1]);

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > largest) {
                largest = nums[i];
            }

            if(nums[i] < smallest) {
                smallest = nums[i];
            }
        }

        return helpGCD(smallest, largest);
    }

    public int helpGCD(int x, int y) {
        for(int i = y; i >= 2; i--) {
            if(x % i == 0 && y % i == 0) {
                return i;
            }
        }

        return 1;
    }
}