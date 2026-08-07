class Solution {
    public int missingNumber(int[] nums) {
        // Arrays.sort(nums); // TC O(Nlogn)
        // int n = nums.length;
        
        // for(int i = 0; i < n-1; i++) { // TC O(n)
        //     if(nums[i+1] - nums[i] != 1) {
        //         return nums[i]+1;
        //     }
        // }
        // return n;

        int ans = 0; 
        int n = nums.length;

        for(int i = 0; i < n ; i++) {
            ans += nums[i];
        }

        int total = (n * (n + 1)) / 2;

        return total - ans;

    }
}

//TC = O(n logn) + O(n) => O(n log n)..
//SC = O(1);


