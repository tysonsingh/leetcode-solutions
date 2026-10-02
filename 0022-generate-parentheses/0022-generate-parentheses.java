class Solution {

    public List<String> generateParenthesis(int n) {
        int open = 0;
        int close = 0;

        List<String> result = new ArrayList<>();

        generate(n, open, close, result, "");

        return result;
    }

    public void generate(int n , int open, int close , List<String> result, String s) {
        if(open == close && open == n) {
            result.add(s);
        }

        if(open < n) {
            generate(n,open + 1, close, result, s+"(");
        }
        if(close < open) {
            generate(n, open, close + 1, result, s+")");
        }

    }
}