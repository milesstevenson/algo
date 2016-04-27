public class Solution {
    /**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public boolean compareStrings(String A, String B) {
        char[] a_arr = A.toCharArray();
        char[] b_arr = B.toCharArray();

        boolean found = false;
        for (int i = 0; i < b_arr.length; i++) {
            found = false;
            for (int j = 0; j < a_arr.length; j++) {
                if (a_arr[j] == b_arr[i]) {
                    a_arr[j] = 'z';
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }
}
