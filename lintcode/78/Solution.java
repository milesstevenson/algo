public class Solution {
    /**
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < strs[0].length(); i++) {
            boolean found = true;
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i))
                    found = false;
            }
            if (found) 
                sb.append(strs[0].charAt(i));
            else
                break;
        }
        return sb.toString();
    }
}
