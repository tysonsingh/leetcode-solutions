/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void deleteNode(ListNode node) {
        
        ListNode temp = node;

        //Iterate till the before last node
        while(temp.next.next != null) {
            temp.val = temp.next.val;
            temp = temp.next;
        }

        //Coping the last node data 
        temp.val = temp.next.val;

        //removing last node refrence
        temp.next = null;

    }
}