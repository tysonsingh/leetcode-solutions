class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Find how many items we can satisfy for BOTH type 1 and type 2 simultaneously
        long bothCount = Math.min(need1, need2);
        
        // We can either buy typeBoth items directly or buy one type1 and one type2 item
        long unitCostBoth = Math.min((long) costBoth, (long) cost1 + cost2);
        long totalCost = bothCount * unitCostBoth;
        
        // Remaining needs after satisfying 'bothCount'
        long rem1 = need1 - bothCount;
        long rem2 = need2 - bothCount;
        
        // For remaining type 1 requirements, use the cheaper option between cost1 and costBoth
        long unitCost1 = Math.min((long) cost1, (long) costBoth);
        totalCost += rem1 * unitCost1;
        
        // For remaining type 2 requirements, use the cheaper option between cost2 and costBoth
        long unitCost2 = Math.min((long) cost2, (long) costBoth);
        totalCost += rem2 * unitCost2;
        
        return totalCost;
    }
}