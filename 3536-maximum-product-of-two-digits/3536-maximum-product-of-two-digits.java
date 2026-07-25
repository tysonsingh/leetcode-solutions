class Solution {
    public int maxProduct(int n) {
        int max1 = -1, max2 = -1;   // largest and second largest digit

        while (n > 0) {
            int d = n % 10;         // current last digit
            if (d > max1) {
                max2 = max1;        // old largest becomes second
                max1 = d;
            } else if (d > max2) {
                max2 = d;
            }
            n /= 10;                // drop the last digit
        }
        return max1 * max2;
    }
}