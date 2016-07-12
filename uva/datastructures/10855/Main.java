import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Miles Stevenson
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task10855 solver = new Task10855();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task10855 {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int N, n;
            char[][] big, small;
            while ((N = in.nextInt()) != 0 && (n = in.nextInt()) != 0) {
                big = processSquare(N, in);
                small = processSquare(n, in);

                for (int i = 0; i < 4; i++) {
                    if (i > 0) {
                        out.print(" ");
                        small = rotate90(small);
                    }
                    out.print(count(big, small));
                }
                out.println();
            }
        }

        private char[][] rotate90(char[][] sq) {
            int n = sq.length;
            char[][] newsq = new char[n][n];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    newsq[j][n - i - 1] = sq[i][j];

            return newsq;
        }

        private int count(char[][] big, char[][] small) {
            int count = 0;
            int N = big.length;
            int n = small.length;

            for (int i = 0; i < N; i++) {
                if (i + n - 1 >= N)
                    break;
                for (int j = 0; j < N; j++) {
                    if (j + n - 1 >= N)
                        break;
                    count += matchFound(big, small, i, j);
                }
            }
            return count;
        }

        private int matchFound(char[][] big, char[][] small, int r, int c) {
            int n = small.length;

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (small[i][j] != big[r + i][c + j])
                        return 0;
            return 1;
        }

        private char[][] processSquare(int sz, InputReader in) {
            char[][] sq = new char[sz][sz];
            for (int i = 0; i < sz; i++) {
                String row = in.nextLine();
                for (int j = 0; j < row.length(); j++)
                    sq[i][j] = row.charAt(j);
            }
            return sq;
        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        public String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public String next() {
            try {
                while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                    tokenizer = new StringTokenizer(nextLine());
                }
                return tokenizer.nextToken();
            } catch (NullPointerException e) {
                return null;
            }
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

