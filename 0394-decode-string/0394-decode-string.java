import java.util.Stack;

class Solution {
    public String decodeString(String s) {
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c != ']') {
                st.push(c);
            } else {
                // 1. Collect substring inside [...]
                StringBuilder tempS = new StringBuilder();
                while (!st.isEmpty() && st.peek() != '[') {
                    tempS.append(st.pop());
                }
                tempS.reverse();

                // 2. Pop '['
                if (!st.isEmpty() && st.peek() == '[') {
                    st.pop();
                }

                // 3. Collect full k (supports multi-digit numbers like 12 or 300)
                int k = 0;
                int base = 1;
                while (!st.isEmpty() && Character.isDigit(st.peek())) {
                    k += (st.pop() - '0') * base;
                    base *= 10;
                }

                // 4. Repeat substring k times and push back onto stack
                String repeated = tempS.toString().repeat(Math.max(0, k));
                for (int j = 0; j < repeated.length(); j++) {
                    st.push(repeated.charAt(j));
                }
            }
        }

        // 5. Build final answer from remaining stack characters
        StringBuilder result = new StringBuilder();
        while (!st.isEmpty()) {
            result.append(st.pop());
        }
        return result.reverse().toString();
    }
}