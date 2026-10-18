class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;

        // 1. Deck ko sort karo
        Arrays.sort(deck);

        // 2. Queue me saare indices (0 se n-1) daalo
        Queue<Integer> qu = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            qu.offer(i);
        }

        int[] result = new int[n];

        // 3. Simulation: Ek card reveal karo, agle ko piche daalo
        for (int i = 0; i < n; i++) {

            // Card ko sahi index par rakho
            int reveal = qu.poll();

            result[reveal] = deck[i];
            
            // Agar queue me cards bache hain, tabhi agle card ko piche daalo
            if (!qu.isEmpty()) {
                qu.offer(qu.poll());
            }
        }
        return result;
    }
}