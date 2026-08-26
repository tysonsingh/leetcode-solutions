class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        //int count = countOnes(k);

        int left = 0;
        int lenString = s.length();

        String windowStr = "";
        int onesCount = 0;
        int lenCount = Integer.MAX_VALUE;

        for(int right = 0; right < lenString; right++) {
            char currChar = s.charAt(right);
            //windowStr.append(currChar);
            if(currChar == '1') onesCount++;
            
            while(onesCount > k) {
                char tempChar = s.charAt(left);
                if(tempChar == '1') onesCount--;
                left++;
            }

            if (onesCount == k) {

                // Remove unnecessary leading zeroes
                while (s.charAt(left) == '0') {
                    left++;
                }

                int currLen = right - left + 1;
                String curr = s.substring(left, right + 1);

                if (currLen < lenCount ||
                    (currLen == lenCount && curr.compareTo(windowStr) < 0)) {

                    lenCount = currLen;
                    windowStr = curr;
                }
            }
        }

        if(lenCount != Integer.MAX_VALUE) return windowStr;

        return "";

    }

    // public int countOnes(int k) {
    //     int count = 0;

    //     for(int i = 0; i < 32; i++ ) {
    //         if( (k & (1 << i)) != 0) {
    //             count++;
    //         }
    //     }

    //     return count;
    // }
}