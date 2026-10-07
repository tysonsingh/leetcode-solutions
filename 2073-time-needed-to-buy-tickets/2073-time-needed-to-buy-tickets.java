class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int countTime = 0;

        Queue<Integer> qu = new ArrayDeque<>();

        for(int n : tickets) {
            qu.offer(n);
        }

        int personIndex = k;
        int personMoney = tickets[k];

        while(personMoney > 0) {
            //Person after buying ticket going back to the queue.
            if(personIndex == 0) {
                if(personMoney <= 1) {
                    countTime++;
                    personMoney--;
                    break;
                }
                else {
                    personMoney--;
                    personIndex = qu.size();
                }
            }
            int rotate = qu.peek() - 1;
            if(rotate == 0) {
                qu.poll();
            }
            else {
                qu.poll();
                qu.offer(rotate);
            }
            countTime++;
            personIndex--;
        }

        return countTime;
    }
}