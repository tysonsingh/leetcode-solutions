class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i);

            if( !st.isEmpty() && st.peek() == c) {
                st.pop();
            }
            else {
                st.push(c);
            }
        }
        String ans = "";

        while(!st.isEmpty()) {
            ans = st.pop() + ans;
        }

        return ans;
    }
}