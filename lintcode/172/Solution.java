public class Solution {
    /** 
     *@param A: A list of integers
     *@param elem: An integer
     *@return: The new length after remove
     */
    public int removeElement(int[] A, int elem) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == elem) {
                count++;
                A[i] = Integer.MAX_VALUE;
            }
        }
        Arrays.sort(A);
        return A.length-count;
    }
}
