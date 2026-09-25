class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> set = dfs(expression);

        List<String> result = new ArrayList<>(set);
        Collections.sort(result);

        return result;
    }

    private Set<String> dfs(String s) {
        Set<String> result = new HashSet<>();

        int level = 0;

        // Handle top-level comma
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '{') {
                level++;
            } 
            else if (c == '}') {
                level--;
            } 
            else if (c == ',' && level == 0) {
                result.addAll(dfs(s.substring(0, i)));
                result.addAll(dfs(s.substring(i + 1)));
                return result;
            }
        }

        // No top-level comma => concatenation
        result.add("");

        for (int i = 0; i < s.length();) {

            Set<String> current;

            if (s.charAt(i) == '{') {
                int start = i;
                int braces = 0;

                while (i < s.length()) {
                    if (s.charAt(i) == '{') braces++;
                    else if (s.charAt(i) == '}') braces--;

                    i++;

                    if (braces == 0) break;
                }

                current = dfs(s.substring(start + 1, i - 1));

            } else {
                current = new HashSet<>();
                current.add(String.valueOf(s.charAt(i)));
                i++;
            }

            // Concatenate
            Set<String> next = new HashSet<>();

            for (String a : result) {
                for (String b : current) {
                    next.add(a + b);
                }
            }

            result = next;
        }

        return result;
    }
}