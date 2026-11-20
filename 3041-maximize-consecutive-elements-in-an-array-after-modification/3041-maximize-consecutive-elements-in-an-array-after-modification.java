import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int lenForPlusOne = dp.getOrDefault(x, 0) + 1;
            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), lenForPlusOne));
            ans = Math.max(ans, dp.get(x + 1));

            int lenForX = dp.getOrDefault(x - 1, 0) + 1;
            dp.put(x, Math.max(dp.getOrDefault(x, 0), lenForX));
            ans = Math.max(ans, dp.get(x));
        }

        return ans;
    }
}