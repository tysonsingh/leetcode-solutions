class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        
        int maxElements = 0;
        int sumElements = 0;
        for(int i = 0; i < k; i++) {
            sumElements += arr[i];
        }

        maxElements = sumElements;
        int count = ((double) (sumElements / k) ) >= threshold ? 1 : 0;

        for(int i = k; i < arr.length; i++) {
            
            sumElements -= arr[i - k];
            sumElements += arr[i];

            if ( ( (double) (sumElements / k) ) >= threshold ) count++;

        }

        return count;
    }
}