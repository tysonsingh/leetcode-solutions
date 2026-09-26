class Solution {
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        int sLen = s.length();
        int tLen = t.length();
        while( i < sLen &&  j < tLen ) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(j);

            if(sChar == tChar) {
                i++;
                j++;
            }
            else{
                j++;
            }
        }

        return i == sLen ;
    }
}