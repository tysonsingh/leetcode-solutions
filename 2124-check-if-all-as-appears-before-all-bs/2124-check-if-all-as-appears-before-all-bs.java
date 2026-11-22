class Solution {
    public boolean checkString(String s) {
        boolean check = true;
        int len = s.length();
        //Checking a
        int index = 0;
        while(len > index && s.charAt(index) != 'b' ) {
            index++;
        }

        //Checking b
        while(len > index && s.charAt(index) != 'a' ) {
            index++;
        }

        return index == s.length();
    }
}