class Solution {
    public int numRescueBoats(int[] people, int limit) {
        int left = 0;
        int right = people.length - 1;
        int count = 0;
        Arrays.sort(people);

        while(left <= right) {
            if(left == right && people[left] <= limit ) {
                count++;
                left++;
            }
            else if(people[left] + people[right] <= limit) {
                count++;
                left++;
                right--;
            }
            else if( people[right] <= limit ) {
                count++;
                right--;
            }
            else if( people[left] <= limit ) {
                count++;
                left++;
            }
            else {
                left++;
                right--;
            }
        }

        return count;
    }
}