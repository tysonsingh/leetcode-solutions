class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        // Sort queries and logs by time
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] ans = new int[m];
        HashMap<Integer, Integer> serverCount = new HashMap<>();
        int left = 0, right = 0;
        int logLen = logs.length;
        
        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int qIndex = sortedQueries[i][1];
            int windowStart = qTime - x;
            
            // Add logs that fall within the right boundary (<= qTime)
            while (right < logLen && logs[right][1] <= qTime) {
                int serverId = logs[right][0];
                serverCount.put(serverId, serverCount.getOrDefault(serverId, 0) + 1);
                right++;
            }
            
            // Remove logs that fall outside the left boundary (< windowStart)
            while (left < logLen && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCount.put(serverId, serverCount.get(serverId) - 1);
                if (serverCount.get(serverId) == 0) {
                    serverCount.remove(serverId);
                }
                left++;
            }
            
            // Servers with zero requests = total servers - servers with at least one request
            ans[qIndex] = n - serverCount.size();
        }
        
        return ans;
    }
}