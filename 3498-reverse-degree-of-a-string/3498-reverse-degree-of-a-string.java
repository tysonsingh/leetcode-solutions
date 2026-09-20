class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        for(int i = 1; i <= s.length(); i++) {
            int ch = ( ('z' - (s.charAt(i-1) ) ) + 1 );
            int product = ch * i;

            sum += product;
        }

        return sum;
    }

}