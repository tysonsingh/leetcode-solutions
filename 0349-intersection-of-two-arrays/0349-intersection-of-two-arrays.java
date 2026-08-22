class Solution {
    // public int[] intersection(int[] nums1, int[] nums2) {
    //     int i = 0;
    //     int j = 0;
    //     Arrays.sort(nums1);
    //     Arrays.sort(nums2);

    //     Set<Integer> ans = new HashSet<>();

    //     while(i < nums1.length && j < nums2.length ) {
    //         if( nums1[i] < nums2[j]) {
    //             i++;
    //         }
    //         else if( nums1[i] > nums2[j] ) {
    //             j++;
    //         }
    //         else {
    //             ans.add(nums1[i]);
    //             i++;
    //             j++;
    //         }
    //     }

    //     return ans.stream().mapToInt(Integer::intValue).toArray();
    // }

    //Optimal
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> hs1 = new HashSet<>();
        Set<Integer> hs2 = new HashSet<>();

        for(int i = 0; i < nums1.length; i++) {
            hs1.add(nums1[i]);
        }

        for(int i = 0; i < nums2.length; i++) {
            if(hs1.contains(nums2[i])) {
                hs2.add(nums2[i]);
            }
        }

        return hs2.stream().mapToInt(Integer::intValue).toArray();
    }
}