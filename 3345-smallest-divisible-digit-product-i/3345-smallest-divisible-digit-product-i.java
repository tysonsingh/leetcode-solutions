class Solution {
    public int smallestNumber(int n, int t) {
        for(int i = n; i <= 100; i++ ) {
            if(helper(i, t)) return i;
        }
        return -1;
    }

    public boolean helper(int n, int t) {
        int ans = 1;
        while( n > 0) {
            int digit = n % 10;
            ans *= digit;
            n /= 10;
        }

        return ans % t == 0;
    }
}