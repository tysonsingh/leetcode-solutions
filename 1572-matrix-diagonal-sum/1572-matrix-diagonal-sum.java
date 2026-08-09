class Solution {
    public int diagonalSum(int[][] mat) {
        /*
        if( mat.length == 1 ) return mat[0][0];
        int n = mat.length;
        int total = 0;
        //Primary Diagonal
        for(int row = 0; row < mat.length; row++) {
            for(int column = 0 ; column < mat[0].length; column++) {
                if(row == column) {
                    total += mat[row][column];
                }
            }
        }

        for(int row = 0; row < mat.length; row++) {
            for(int column = 0; column < mat[0].length; column++) {
                if(column == (n - row - 1)) {
                    total += mat[row][column];
                }
            }
        }

        if(n % 2 != 0) return total -= mat[n/2][n/2];

        return total;
        */

        //Optmial Approach
        int n = mat.length;
        int total = 0;

        for(int row = 0; row < n; row++) {
            //First Diagonal
            total += mat[row][row];

            //Second diagonal
            total += mat[row][n - 1 - row];
        }

        if(n % 2 != 0)
            return total -= mat[n/2][n/2];

        return total;
    }
}