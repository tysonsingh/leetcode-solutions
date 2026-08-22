class Solution {
    public boolean checkDivisibility(int n) {
        if(n < 10) return false;

        int num = n;

        int sum = 0;
        int mul = 1;

        while(num != 0) {
            int digit = num % 10;
            sum += digit;
            mul *= digit;

            num /= 10;
        }

        if(n % (sum + mul) == 0 ) {
            return true;
        }

        return false;
    }
}