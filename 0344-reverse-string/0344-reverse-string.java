class Solution {
    public void reverseString(char[] s) {
        // int len = s.length;
        // for(int i = 0; i < len/2 ; i++) {
        //     char temp = s[i] ;
        //     s[i] = s[len - i - 1];
        //     s[len - i - 1] = temp;
        // }
        helper(s,0, s.length - 1);
    }

    public void helper(char[] s, int left , int right) {
        if(left >= right) return;

        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;

        left++;
        right--;
        helper(s, left, right);
    }


}