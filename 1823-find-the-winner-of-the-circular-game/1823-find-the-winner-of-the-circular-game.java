class Solution {
    public int findTheWinner(int n, int k) {
        Queue<Integer> qu = new ArrayDeque<>();

        for(int i = 1; i <= n; i++) {
            qu.offer(i);
        }

        while(qu.size() > 1) {
            
            int rule = k;

            while( rule > 1) {
                int skip = qu.poll();
                qu.offer(skip);
                rule--;
            }

            qu.poll();
        }

        if(!qu.isEmpty()) {
            return qu.poll();
        }

        return -1;
    }
}