class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
                if( c == '(') {
                    st.push(i);
                    continue;
                }

                if( c == ')') {
                    if(st.isEmpty()) {
                        set.add(i);
                    }
                    else {
                        st.pop();
                    }
                }
        }

        while(!st.isEmpty()) {
            set.add(st.pop());
        }

        StringBuilder str = new StringBuilder();

        for(int j = 0; j < s.length(); j++) {
            if(!set.contains(j)) {
                str.append(s.charAt(j));
            }
        }

        return str.toString();

    }
}