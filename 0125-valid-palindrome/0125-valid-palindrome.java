class Solution {
    // public boolean isPalindrome(String str) {
    //     int i = 0;
    //     int j = str.length() - 1;

    //     while( i <= j ) {
    //         char iChar = Character.toLowerCase(str.charAt(i));
    //         char jChar = Character.toLowerCase(str.charAt(j));

    //         if(Character.isLetterOrDigit(iChar)) {
    //             if(Character.isLetterOrDigit(jChar)) {
    //                 if(iChar == jChar) {
    //                     i++;
    //                     j--;
    //                     continue;
    //                 }
    //                 else {
    //                     return false;
    //                 }
    //             }
    //             else {
    //                 j--;
    //                 continue;
    //             }
    //         }
    //         else {
    //             i++; 
    //             continue;
    //         }
    //     }
    //     return true;
    // }

    public boolean isPalindrome(String str) {
        //contract : ye hme true ya false laakr dega at the end agr humara str palindrome hai ki ni
        //base : jb last jo ki left or right equal ho to s palindrome hai.
        // ek kadam : str ke char ek index ko left se ++ or right -- krke, problem ko simpler krenge using recursion :
        if(str.length() == 1) return true;

        StringBuilder stb = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(Character.isLetterOrDigit(c)) {
                stb.append(Character.toLowerCase(c));
            }
        }
        return helper(stb.toString(),0, stb.length() - 1);

    }

    public boolean helper(String str, int left , int right) {
        if(left >= right ) return true;

        char leftChar = str.charAt(left);
        char rightChar = str.charAt(right);

        if(leftChar != rightChar) return false;

        return helper(str,left + 1, right - 1);
    }
}