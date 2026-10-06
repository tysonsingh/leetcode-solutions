class MinStack {
    
    List<Integer> st;
    List<Integer> storeMin;
    
    public MinStack() {
        st = new ArrayList<>();
        storeMin = new ArrayList<>();   
    }
    
    public void push(int value) {
        
        if(st.size() != 0) {
            int min = Math.min(storeMin.get(storeMin.size() - 1), value);
            storeMin.add(min);
        }
        else {
            storeMin.add(value);
        }
        st.add(value);
    }
    
    public void pop() {
        st.remove(st.size() - 1);
        storeMin.remove(storeMin.size() - 1);
    }
    
    public int top() {
        if(st.size() != 0) {
            return st.get(st.size() - 1);
        }
        
        return -1;
    }
    
    public int getMin() {
        return storeMin.get(st.size() - 1);
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */