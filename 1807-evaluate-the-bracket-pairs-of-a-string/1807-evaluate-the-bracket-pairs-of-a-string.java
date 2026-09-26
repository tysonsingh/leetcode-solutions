class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        HashMap<String,String> mapp = new HashMap<>();

        for(List<String> pair : knowledge) {
            mapp.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if( ch == '(' ) {
                int j = i + 1;

                while( s.charAt(j) != ')') {
                    j++;
                }

                String key = s.substring(i+1, j);

                if(mapp.containsKey(key)) {
                    ans.append(mapp.get(key));
                }
                else {
                    ans.append("?");
                }

                i = j;
            }
            else {
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }
}