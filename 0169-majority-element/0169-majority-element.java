class Solution {
    // public int majorityElement(int[] nums) {
    //     int candidate = nums[0];
    //     int count = 1;

    //     for(int i = 1; i < nums.length; i++) {
    //         if(count == 0) {
    //             candidate = nums[i];
    //         }

    //         if(nums[i] == candidate) {
    //             count++;
    //         }
    //         else {
    //             count--;
    //         }
    //     }

    //     return candidate;
    // }

    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> counts = new HashMap<>();
        int limit = nums.length / 2;
        int maxFreq = 0;
        int number = -1;

        for(int n : nums) {
            counts.put(n, counts.getOrDefault(n, 0) + 1);
            if(counts.get(n) > limit && counts.get(n) > maxFreq) {
                maxFreq = counts.get(n);
                number = n;
            }
        }

        return number;
    }
}