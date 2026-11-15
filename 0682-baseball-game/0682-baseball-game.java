class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < operations.length; i++) {
            String str = operations[i];
            if(str.equals("C")) {
                if(!st.isEmpty()) {
                    st.pop();
                }
            }
            else if(str.equals("D")) {
                int doublevalue = st.peek() * 2;
                st.push(doublevalue);
            }
            else if(str.equals("+")) {
                int firstEle = st.pop();
                int secondEle = st.peek();

                int thirdEle = firstEle + secondEle;

                st.push(firstEle);
                st.push(thirdEle);
            }
            else {
                st.push(Integer.parseInt(str));
            }
        }

        int totalSum = 0;

        while(!st.isEmpty()) {
            totalSum += st.pop();
        }

        return totalSum;
    }
}