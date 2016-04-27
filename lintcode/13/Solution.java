class Solution {
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        if (null == source || null == target)
            return -1;
        if (target.isEmpty())
            return 0;
            
        for (int j = 0; j < source.length(); j++) {
            if (target.charAt(0) == source.charAt(j)) {
                if (foundSubIndex(source, target, j))
                    return j;
            }
        }
        return -1;
    }
    
    private boolean foundSubIndex(String src, String tar, int idx) {
        for (int i = 0; i < tar.length(); i++) {
            if (idx + i >= src.length() || tar.charAt(i) != src.charAt(idx+i))
                return false;
        }
        return true;
    }
}
