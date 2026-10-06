class Solution {
    public int minAddToMakeValid(String s) {
        int opening = 0;
        int closing = 0;

        int resolved = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                opening++;
            }
            else {
                if (opening > 0) {
                    opening--;
                }
                else {
                    resolved++;
                }
            }
        }

        return resolved + opening;
    }
}