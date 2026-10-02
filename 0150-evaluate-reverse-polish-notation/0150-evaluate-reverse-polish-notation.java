class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();

        for(String token : tokens) {
            if(isOperator(token)) {
                int firstNum = st.pop();
                int secondNum = st.pop();

                int ans = -1;

                if(token.equals("+")) {
                    ans = secondNum + firstNum;
                }
                else if(token.equals("-")) {
                    ans = secondNum - firstNum;
                }
                else if(token.equals("/")) {
                    ans = secondNum / firstNum;
                }
                else {
                    ans = secondNum * firstNum;
                }

                st.push(ans);
            }
            else {
                st.push(Integer.parseInt(token));
            }
        }

        return st.pop();
    }

    public boolean isOperator( String c ) {
        return (c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*"));
    }
}