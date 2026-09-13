class Solution {

    //Two Pointers
    public int[] sortedSquares(int[] nums) {
        int left = 0; 
        int right = nums.length - 1;
        int[] ans = new int[nums.length];

        int idx = right;

        while (left <= right) {
            int leftPow = (int)Math.pow(nums[left],2);
            int rightPow = (int)Math.pow(nums[right],2);

            if(leftPow > rightPow) {
                ans[idx--] = leftPow;
                left++; 
            }
            else {
                ans[idx--] = rightPow;
                right--;
            }
        }

        return ans;
    }

    /* MergeSort Method 
    public int[] sortedSquares(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            nums[i] = (int)Math.pow(nums[i],2);
        }
        mergeSort(nums, 0 , nums.length-1);
        return nums;
    }

    public void mergeSort(int[] nums, int low, int high) {
        if(low >= high) return; 
        int mid = (low + high) / 2;

        mergeSort(nums,low,mid);
        mergeSort(nums,mid+1, high);
        merge(nums,low,mid,high);
    }

    public void merge(int[] nums, int low, int mid, int high) {
        int left = low;
        int right = mid+1;

        List<Integer> temp = new ArrayList<>();

        while(left <= mid && right <= high) {
            if(nums[left] <= nums[right]) {
                temp.add(nums[left]);
                left++;
            }
            else {
                temp.add(nums[right]);
                right++;
            }
        }

        while(left <= mid) {
            temp.add(nums[left]);
            left++;
        }

        while(right <= high) {
            temp.add(nums[right]);
            right++;
        }

        for(int i = 0; i < temp.size(); i++) {
            nums[low + i] = temp.get(i);
        }

    }
    */


}