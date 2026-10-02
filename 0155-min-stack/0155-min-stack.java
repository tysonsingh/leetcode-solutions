class MinStack {
    private ArrayList<Integer> arr;
    private ArrayList<Integer> minValueArr;
    private int top;

    public MinStack() {
        arr = new ArrayList<>();
        minValueArr = new ArrayList<>();
        top = -1;
    }
    
    public void push(int value) {
        if(top != -1) {
            top++;
            arr.add(value);
            minValueArr.add(Math.min(minValueArr.get(top-1),value));
        }
        else {
            top++;
            arr.add(value);
            minValueArr.add(value);
        }
    }
    
    public void pop() {
        if(top != -1) {
            arr.remove(top);
            minValueArr.remove(top);
            top--;
        }
    }
    
    public int top() {
        if(top != -1) {
            return arr.get(top);
        }
        return -1;
    }
    
    public int getMin() {
        return minValueArr.get(top);
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