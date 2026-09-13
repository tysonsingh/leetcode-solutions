class Solution {
    public String reverseVowels(String s) {
        int left = 0; 
        int right = s.length() - 1;

        StringBuilder ans = new StringBuilder(s);

        while(left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            if(checkVowel(Character.toLowerCase(leftChar)) && 
                checkVowel(Character.toLowerCase(rightChar)) ) {
                    ans.setCharAt(left, rightChar);
                    ans.setCharAt(right, leftChar);
                    left++;
                    right--;
            }
            else if(! checkVowel(Character.toLowerCase(leftChar)) ) {
                ans.setCharAt(left, leftChar);
                left++;
            }
            else {
                ans.setCharAt(right, rightChar);
                right--;
            }

        }

        return ans.toString();
    }

    public boolean checkVowel(char c) {
        return ( c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' );
    }
}