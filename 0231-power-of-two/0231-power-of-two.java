class Solution {

    // Bit Manipulation :
    // public boolean isPowerOfTwo(int n) {
    //     return (n > 0) && ( (n & (n-1)) == 0 );
    // }

    // Recursion : 
    public boolean isPowerOfTwo(int n) {
        if( n == 1 ) {
            return true;
        }
        if( n <= 0 || n % 2 == 1 ) {
            return false;
        }

        return isPowerOfTwo(n / 2);

    }
}