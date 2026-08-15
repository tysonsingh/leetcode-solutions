class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int row = m - 1;
        int column = 0;
        int ans = 0;

        while( row >= 0 && column < n) {

            if(grid[row][column] >= 0) {
                column++;
            }
            else {
                ans += (n - column);
                row--;
            }
        }

        return ans;
    }
}