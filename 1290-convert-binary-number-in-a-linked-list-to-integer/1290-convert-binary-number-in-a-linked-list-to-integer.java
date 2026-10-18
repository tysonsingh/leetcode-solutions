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
    public int getDecimalValue(ListNode head) {
        StringBuilder bits = new StringBuilder();

        ListNode temp = head;

        while(temp != null) {
            bits.append(temp.val);

            temp = temp.next;
        }

        int ans = 0;
        int powerOfTwo = 1; //assuming first 2^0 = 1

        for(int i = bits.length() - 1; i >= 0; i--) {
            if(bits.charAt(i) == '1') {
                ans += powerOfTwo;
            }

            powerOfTwo *= 2;
        }

        return ans;
    }
}