class Solution {
    public int getLucky(String s, int k) {
        int iteration = 0;
        int sum = 0;
        
        while(iteration < s.length()){
            char c = s.charAt(iteration++);
            int n = (c - 'a') + 1;

            while ( n != 0 ) {
                sum += n % 10;
                n /= 10;
            }
        }

        return helper(sum , k - 1);
    }

    public int helper(int num, int k) {
        if( k == 0 || num < 10 ) return num;
        
        int sum = 0;
        while( num != 0) {
            sum += num % 10;
            num /= 10;
        }

        return helper(sum , k - 1);
    }
}