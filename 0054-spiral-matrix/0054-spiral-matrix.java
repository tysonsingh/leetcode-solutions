class Solution {
    
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        int left = 0, top = 0;
        int right = matrix[0].length - 1;
        int bottom = matrix.length - 1;

        while(left <= right && top <= bottom) {

            // Top → Left to Right
            for(int col = left; col <= right; col++) {
                spiral.add(matrix[top][col]);
            }
            top++;

            // Right → Top to Bottom
            for(int row = top; row <= bottom; row++) {
                spiral.add(matrix[row][right]);
            }
            right--;

            // Bottom → Right to Left
            if(top <= bottom) {
                for(int col = right; col >= left; col--) {
                    spiral.add(matrix[bottom][col]);
                }
                bottom--;
            }

            // Left → Bottom to Top
            if(left <= right) {
                for(int row = bottom; row >= top; row--) {
                    spiral.add(matrix[row][left]);
                }
                left++;
            }
        }

        return spiral;
    }
}