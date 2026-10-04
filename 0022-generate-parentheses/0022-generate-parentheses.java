class Solution {

    // public List<String> generateParenthesis(int n) {
    //     int open = 0;
    //     int close = 0;

    //     List<String> result = new ArrayList<>();

    //     generate(n, open, close, result, "");

    //     return result;
    // }

    // public void generate(int n , int open, int close , List<String> result, String s) {
    //     if(open == close && open == n) {
    //         result.add(s);
    //     }

    //     if(open < n) {
    //         generate(n,open + 1, close, result, s+"(");
    //     }
    //     if(close < open) {
    //         generate(n, open, close + 1, result, s+")");
    //     }

    // }

    public List<String> generateParenthesis(int n) {
        List<String> ans =  new ArrayList<>();
        int open = 0;
        int close = 0;
        
        StringBuilder str = new StringBuilder();
        
        generate(ans, open, close, n, str);
        
        return ans;
        
    }
    
    public void generate(List<String> ans, int open, int close, int n, StringBuilder str) {
        if(open == close && open == n) {
            ans.add(str.toString());
        }
        
        if(open < n) {
            generate(ans, open + 1, close, n, str.append("("));
            //Because of backtracking we need to delete the char.
            str.deleteCharAt(str.length() - 1);
        }
        
        if(close < open) {
            generate(ans, open, close + 1, n, str.append(")"));
            str.deleteCharAt(str.length() - 1);
        }
        
    }
}