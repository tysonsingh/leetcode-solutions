class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Priority queue for available servers: ordered by weight, then index
        // Each element: [weight, index]
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Priority queue for unavailable servers: ordered by free time, weight, index
        // Each element: [freeTime, weight, index]
        PriorityQueue<long[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[2], b[2]);
        });

        // Initialize all servers as free
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // If no servers are available, jump time to when the next server becomes free
            if (freeServers.isEmpty() && !busyServers.isEmpty()) {
                currentTime = Math.max(currentTime, busyServers.peek()[0]);
            }

            // Release all servers that have finished their tasks by currentTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                long[] busy = busyServers.poll();
                freeServers.offer(new int[] { (int) busy[1], (int) busy[2] });
            }

            // Assign the current task j to the best available server
            int[] server = freeServers.poll();
            int weight = server[0];
            int index = server[1];

            ans[j] = index;
            
            // Push the server into the busy queue with its new release time (long[])
            busyServers.offer(new long[] { currentTime + tasks[j], weight, index });
        }

        return ans;
    }
}