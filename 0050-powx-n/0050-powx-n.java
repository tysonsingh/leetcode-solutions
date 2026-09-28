class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1;

        long num = n;

        if(num < 0) {
            x = 1/x;
            num *= -1;
        }

        return helper(x, num);
    }

    public double helper( double x, long n) {
        if(n == 1) return x;

        if(n % 2 == 1) {
            return x * helper(x, n - 1);
        }
        else {
            return helper(x * x, n / 2);
        }
    }












    // public double myPow(double x, int n) {
    //     if( n == 0 ) return 1;

    //     long num = n; 

    //     if( n < 0 ) {
    //         x = 1 / x;
    //         num *= -1;
    //     }

    //     return helper(x, num);
    // }

    // public double helper(double x, long n ) {
    //     if( n == 0 ) return 1; 

    //     if( n % 2 == 0) {
    //         return helper( x * x , n / 2);
    //     }

    //     return x * helper( x , n - 1);
    // }


}