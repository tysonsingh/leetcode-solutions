class Solution {
    // public void merge(int[] nums1, int m, int[] nums2, int n) {
    //     int left = 0;
    //     int right = 0;
        
    //     List<Integer> temp = new ArrayList<>();

    //     while(left <= m-1 && right <= n-1) {
    //         if(nums1[left] <= nums2[right]) {
    //             temp.add(nums1[left]);
    //             left++;
    //         }
    //         else {
    //             temp.add(nums2[right]);
    //             right++;
    //         }
    //     }

    //     while(left <= m-1) {
    //         temp.add(nums1[left]);
    //         left++;
    //     }

    //     while(right <= n-1) {
    //         temp.add(nums2[right]);
    //         right++;
    //     }

    //     for(int i = 0; i < temp.size(); i++) {
    //         nums1[i] = temp.get(i);
    //     }

    // }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        int r1 = m -1 ;
        int r2 = n -1;
        int write = nums1.length - 1;

        while(r1 >= 0 && r2 >= 0) {
            if(nums1[r1] >= nums2[r2]) {
                nums1[write] = nums1[r1];
                write--;
                r1--;
            }
            else {
                nums1[write] = nums2[r2];
                r2--;
                write--;
            }
        }

        while( r1 >= 0) {
            nums1[write] = nums1[r1];
            write--;
            r1--;
        }

        while( r2 >= 0) {
            nums1[write] = nums2[r2];
            write--;
            r2--;
        }
    }
}