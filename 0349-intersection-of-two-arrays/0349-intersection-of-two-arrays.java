class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        Set<Integer> ans = new HashSet<>();

        while(i < nums1.length && j < nums2.length ) {
            if( nums1[i] < nums2[j]) {
                i++;
            }
            else if( nums1[i] > nums2[j] ) {
                j++;
            }
            else {
                ans.add(nums1[i]);
                i++;
                j++;
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}