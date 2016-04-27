public class Solution {
    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            char[] c_arr = s.toCharArray();
            Arrays.sort(c_arr);
            String a_s = new String(c_arr);
            
            if (!map.containsKey(a_s)) {
                map.put(a_s, new ArrayList<String>());
            }
            map.get(a_s).add(s);
        }
        
        List<String> ans = new ArrayList<>();
        for (String s : map.keySet()) {
            if (map.get(s).size() > 1) 
                ans.addAll(map.get(s));
        }
        
        return ans;
    }
}
