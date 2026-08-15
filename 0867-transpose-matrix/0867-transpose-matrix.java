class Solution {
    public int[][] transpose(int[][] matrix) {
        int rowLen = matrix.length;
        int columnLen = matrix[0].length;

        int[][] ans = new int[columnLen][rowLen];

        for(int row = 0; row < rowLen; row++) {
            for(int column = 0; column < columnLen; column++) {
                ans[column][row] = matrix[row][column];
            }
        }

        return ans;
    }
}