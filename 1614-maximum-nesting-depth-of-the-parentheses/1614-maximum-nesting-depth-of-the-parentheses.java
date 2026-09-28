class Solution {
    public int maxDepth(String s) {
        int countMax = 0;
        int curr = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                curr++;
            }
            else if(s.charAt(i) == ')') {
                countMax = Math.max(countMax, curr);
                curr--;
            }
            else {

            }
        }
        return countMax;
    }
}