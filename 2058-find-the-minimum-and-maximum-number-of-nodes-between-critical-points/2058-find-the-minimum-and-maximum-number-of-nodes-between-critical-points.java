/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1, last = -1;
        int minDist = Integer.MAX_VALUE;
        int idx = 1;                       // 1-based position of cur

        ListNode prev = head;
        ListNode cur = head.next;

        while (cur != null && cur.next != null) {
            idx++;                         // cur is now at position idx

            boolean isMaxima = cur.val > prev.val && cur.val > cur.next.val;
            boolean isMinima = cur.val < prev.val && cur.val < cur.next.val;

            if (isMaxima || isMinima) {
                if (first == -1) {
                    first = idx;           // first critical point found
                } else {
                    minDist = Math.min(minDist, idx - last);
                }
                last = idx;                // always the most recent one
            }

            prev = cur;
            cur = cur.next;
        }

        if (first == last) return new int[]{-1, -1};
        return new int[]{minDist, last - first};
    }
}