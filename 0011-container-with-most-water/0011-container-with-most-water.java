class Solution {
    public int maxArea(int[] height) {
        // int maxA = 0;
        // int len = height.length;
        // for(int i = 0; i < len; i++) {
        //     int curr_area = 0;
        //     for(int j = i + 1;  j < len; j++) {
        //         curr_area = (j - i) * (Math.min(height[i],height[j]));
        //         maxA = Math.max(curr_area, maxA);
        //     }
        // }

        // return maxA;


        //=================================================
        //Optimized way
        int len = height.length;
        int i = 0; 
        int j = len - 1;
        int maxA = 0;

        while( i < j) {
            int curr_Area = (Math.min(height[i],height[j]) * (j - i));
            maxA = Math.max(curr_Area, maxA);
            if( height[i] < height[j]) {
                i++;
            }
            else {
                j--;
            }
        }

        return maxA;
    }

    

    //==========================================
    //Optimized


}