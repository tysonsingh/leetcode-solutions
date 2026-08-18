class Solution {
    public int largestInteger(int[] nums, int k) {

        HashMap<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i <= nums.length - k; i++) {

            HashSet<Integer> seen = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                seen.add(nums[j]);
            }

            for (int num : seen) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
        }

        int ans = -1;

        for (int num : count.keySet()) {
            if (count.get(num) == 1) {
                ans = Math.max(ans, num);
            }
        }

        return ans;
    }
}