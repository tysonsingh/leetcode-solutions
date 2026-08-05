class Solution {
    public int totalFruit(int[] fruits) {
        HashMap<Integer,Integer> typeOfFruits = new HashMap<>();
        int left = 0;
        int maxTrees = Integer.MIN_VALUE;

        for(int right = 0; right < fruits.length; right++ ) {
            
            typeOfFruits.put(fruits[right], typeOfFruits.getOrDefault(fruits[right],0) +1);

            while(typeOfFruits.size() > 2){
                typeOfFruits.put(fruits[left], typeOfFruits.get(fruits[left]) -1);
                if(typeOfFruits.get(fruits[left]) == 0) {
                    typeOfFruits.remove(fruits[left]);
                }
                left++;
            }

            maxTrees = Math.max(maxTrees, right - left + 1);
        }

        return maxTrees;
    }
}