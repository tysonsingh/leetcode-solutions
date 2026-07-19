class Solution {
    public String smallestSubsequence(String s) {

        int[] freq = new int[26];

        // Count frequency of every character
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // Current character is being processed
            freq[ch - 'a']--;

            // Already present in answer
            if (visited[ch - 'a']) {
                continue;
            }

            // Remove bigger characters if they appear later again
            while (!stack.isEmpty()
                    && stack.peek() > ch
                    && freq[stack.peek() - 'a'] > 0) {

                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }

            stack.push(ch);
            visited[ch - 'a'] = true;
        }

        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }

        return ans.reverse().toString();
    }
}