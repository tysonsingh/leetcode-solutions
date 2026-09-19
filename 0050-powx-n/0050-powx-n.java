class Solution {
    public double myPow(double x, int n) {
        //Contract : mujhe double mai return milega of x ke power n ka.
        //Base : jb n == 0 hoga uswaqt hum num to return kr denge.
        //Ek kadam : simpler krne ke liye hm T(n/2) use krenge kese : 
        /*            x * x * myPow(x, n / 2) if even , if odd x * myPow(x, n - 1)
        */

        //Handling 0 power it should be 1;
        if(n == 0) return 1;
        long num = n;
        //Negative scenario
        if( n < 0) {
            x = (double) 1/x;
            num *= -1;
        }

        return helper(x, num);
    }

    public double helper(double x , long n) {
        if(n == 0 )  return 1;

        if(n % 2 == 0) {
            return helper(x * x, n / 2);
        }

        return x * helper(x, n - 1);
    }
}