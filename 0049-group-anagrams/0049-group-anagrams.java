class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> map = new HashMap<>();

        for(int i = 0; i < strs.length; i++) {
            String key = getHashKey(strs[i]);

            if(map.containsKey(key)) {
                map.get(key).add(strs[i]);
            }
            else {
                List<String> newList = new ArrayList<>();
                newList.add(strs[i]);
                map.put(key, newList);
            }
        }

        return new ArrayList<>(map.values());

    }

    public String getHashKey(String str) {
        StringBuilder strKey = new StringBuilder();

        int[] freq = new int[26];

        for(char c : str.toCharArray()) {
            freq[c - 'a']++;
        }

        for(int i = 0; i < 26; i++) {
            strKey.append(freq[i]);
            strKey.append('#');
        }

        return strKey.toString();
    }
}