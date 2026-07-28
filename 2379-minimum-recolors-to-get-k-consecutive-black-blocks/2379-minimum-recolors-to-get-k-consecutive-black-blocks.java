class Solution {
    public int minimumRecolors(String blocks, int k) {
        int countW = 0;
        int min = 0;

        for(int i = 0; i < k; i++) {
            char curr = blocks.charAt(i);
            if(curr == 'W') {
                countW++;
            }
        }

        min = countW;

        for(int i = k ; i < blocks.length(); i++) {
            //Remove
            char prevChar = blocks.charAt(i - k);
            if (prevChar == 'W') countW--;

            //Add
            char currChar = blocks.charAt(i);
            if( currChar == 'W') {
                countW++;
            }

            min = Math.min(min,countW);
        }

        return min;
    }
}