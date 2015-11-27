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
 * @author Miles Stevenson (miles.d.stevenson@gmail.com)
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task10189 solver = new Task10189();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task10189 {
        int n;
        int m;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int field = 1;
            while ((n = in.nextInt()) != 0 && (m = in.nextInt()) != 0) {
                if (field != 1)
                    out.println();
                out.println("Field #" + field + ":");

                char[][] game = new char[n][m];
                for (int i = 0; i < n; i++) {
                    String row = in.nextLine();
                    for (int j = 0; j < m; j++) {
                        game[i][j] = row.charAt(j);
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (game[i][j] == '*') {
                            continue;
                        }
                        int count = 0;
                        int col = i;
                        int row = j - 1;
                        if (valid(col, row) && (game[col][row] == '*')) {
                            count++;
                        }

                        col = i - 1;
                        row = j - 1;
                        if (valid(col, row) && (game[col][row] == '*')) {
                            count++;
                        }

                        col = i - 1;
                        row = j;
                        if (valid(col, row) && (game[col][row] == '*')) {
                            count++;
                        }

                        col = i - 1;
                        row = j + 1;
                        if (valid(col, row) && (game[col][row] == '*')) {
                            count++;
                        }

                        col = i;
                        row = j + 1;
                        if (valid(col, row) && (game[col][row] == '*')) {
                            count++;
                        }

                        col = i + 1;
                        row = j + 1;
                        if (valid(col, row) && (game[col][row] == '*')) {
                            count++;
                        }

                        col = i + 1;
                        row = j;
                        if (valid(col, row) && (game[col][row] == '*')) {
                            count++;
                        }

                        col = i + 1;
                        row = j - 1;
                        if (valid(col, row) && (game[col][row] == '*')) {
                            count++;
                        }
                        game[i][j] = (char) ('0' + count);
                    }
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        out.print(game[i][j]);
                    }
                    out.println();
                }
                field++;
            }
        }

        private boolean valid(int row, int col) {
            return (row > -1 && row < n) && (col > -1 && col < m);
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

