class RecentCounter {
    Queue<Integer> qu;

    public RecentCounter() {
        qu = new ArrayDeque<>();
    }
    
    public int ping(int t) {
        qu.offer(t);

        int range1 = t - 3000;
        int range2 = t;
        while (!(qu.peek() >= range1 && qu.peek() <= range2) ) {
            qu.poll();
        }

        return qu.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */