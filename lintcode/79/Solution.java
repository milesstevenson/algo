public class Solution {
    /**
     * @param A, B: Two string.
     * @return: the length of the longest common substring.
     */
    public int longestCommonSubstring(String A, String B) {
        int N = A.length(); int M = B.length();
        int[][] matrix = new int[N+1][M+1];
        for (int i = 0; i < N+1; i++)
            Arrays.fill(matrix[i], 0);
        
        int longest = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    matrix[i+1][j+1] = matrix[i][j] + 1;
                    longest = Math.max(longest, matrix[i+1][j+1]);
                }
                else {
                    matrix[i+1][j+1] = 0;
                }
            }
        }
        return longest;
    }
}
