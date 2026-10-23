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
    public ListNode deleteDuplicates(ListNode head) {
        
        ListNode temp = head;

        while(temp != null && temp.next != null) {
            if( temp.val == temp.next.val ) {
                //skip the dublicate node but stay on same at 'temp' to check next node, don't advance.
                temp.next = temp.next.next;
            }
            else {
                //Values are distinct, advance the node.
                temp = temp.next;
            }
        }

        return head;
    }
}