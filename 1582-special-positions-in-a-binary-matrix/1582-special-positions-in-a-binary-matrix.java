class Solution {
    public int numSpecial(int[][] mat) {
        int[] rowsArr = new int[mat.length];
        int[] columnsArr = new int[mat[0].length];

        int count = 0;

        //filling count of 1's in row and columns:
        //Row
        for(int row = 0; row < mat.length; row++) {
            for(int column = 0; column < mat[0].length; column++) {
                if(mat[row][column] == 1) {
                    rowsArr[row] += 1;
                    columnsArr[column] += 1;
                }
            }
        }

        for(int row = 0; row < mat.length; row++) {
            for(int column = 0; column < mat[0].length; column++) {
                if(mat[row][column] == 0) continue;

                if(mat[row][column] == 1 && rowsArr[row] == 1 && columnsArr[column] == 1) count++;
            }
        }

        return count;
    }
}