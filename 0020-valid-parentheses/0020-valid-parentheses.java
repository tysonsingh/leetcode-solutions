class Solution {
    public boolean isValid(String s) {
        Deque<Character> st = new ArrayDeque<>();

        int sLen = s.length();
        
        for(int i = 0; i < sLen; i++) {
            char ch = s.charAt(i);

            if(ch == '(' || ch == '[' || ch == '{') {
                st.push(ch);
            }
            else if( ch == ')' || ch == ']' || ch == '}') {
                if (st.isEmpty()) {
                    return false;
                }

                if( ch == ')' ) {
                    if( st.peek() == '(' ) {
                        st.pop();
                    }
                    else {
                        return false;
                    }
                }
                else if( ch == ']') {
                    if( st.peek() == '[') {
                        st.pop();
                    }
                    else {
                        return false;
                    }
                }
                else {
                    if( st.peek() == '{') {
                        st.pop();
                    }
                    else {
                        return false;
                    }
                }
            }
        }

        return st.isEmpty();
    }
}