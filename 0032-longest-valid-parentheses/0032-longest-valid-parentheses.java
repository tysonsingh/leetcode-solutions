class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();

        st.push(-1);                    
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);               // index push, character nahi
            } else {
                st.pop();
                if (st.isEmpty()) {
                    st.push(i);           // ye ')' extra tha, ab yahi naya base
                } else {
                    max = Math.max(max, i - st.peek());
                }
            }
        }
        return max;
    }
}