class Solution {
    public double findMaxAverage(int[] nums, int k) {
        
        double maxAverage = 0;
        double elemSum = 0;

        //0 se k tk ka window create kiya
        for(int i = 0; i < k; i++) {
            elemSum += nums[i];
        }

        //Make store the first window as average sum;
        maxAverage = (double) (elemSum / k);

        //k ke baad ka each window iterate krke Max find krna.
        for(int i = k ; i < nums.length; i++) {
            elemSum -= nums[i - k];
            elemSum += nums[i];

            maxAverage = Math.max(maxAverage, (double)(elemSum/k));
        }

        return maxAverage;
    }
}