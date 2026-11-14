import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long totalCount = 0;
        long currentOperations = 0;

        // Monotonic non-increasing deque from front (leftmost/largest) to back (rightmost/smallest)
        // Each entry stores: [value, count of elements clamped to this value]
        Deque<long[]> deque = new ArrayDeque<>();

        int right = n - 1;
        for (int left = n - 1; left >= 0; left--) {
            // 1. Expand window to the left by adding nums[left]
            long count = 1;
            while (!deque.isEmpty() && deque.peekFirst()[0] < nums[left]) {
                long[] front = deque.pollFirst();
                long prevVal = front[0];
                long prevCount = front[1];

                // Raise all elements in that group up to nums[left]
                currentOperations += (nums[left] - prevVal) * prevCount;
                count += prevCount;
            }
            deque.offerFirst(new long[]{nums[left], count});

            // 2. Shrink window from the right while operations exceed k
            while (currentOperations > k) {
                long[] back = deque.peekLast();
                // The rightmost element in the window is currently clamped to back[0]
                // Removing nums[right] saves (clamped_value - original_value) operations
                currentOperations -= (back[0] - nums[right]);

                if (back[1] == 1) {
                    deque.pollLast();
                } else {
                    back[1]--;
                }
                right--;
            }

            // 3. Every subarray starting at `left` and ending anywhere in [left..right] is valid
            totalCount += (right - left + 1);
        }

        return totalCount;
    }
}