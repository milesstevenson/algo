import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
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
        OutputWriter out = new OutputWriter(outputStream);
        Task11581 solver = new Task11581();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task11581 {
        private int[] dirCol = {-1, 0, 1, 0};
        private int[] dirRow = {0, -1, 0, 1};

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int n = in.nextInt();
            int[][] g;

            while ((n--) > 0) {
                g = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    String row = in.next();
                    for (int j = 0; j < 3; j++)
                        g[i][j] = Integer.valueOf(String.valueOf(row.charAt(j)));
                }
                int index = zeroedOut(g) ? -1 : findGreatestIndex(g);
                out.printLine(index);
            }
        }

        private int findGreatestIndex(int[][] g) {
            int[][] fg = f(g);
            if (zeroedOut(fg))
                return 0;
            return findGreatestIndex(fg) + 1;
        }

        private boolean zeroedOut(int[][] g) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (g[i][j] != 0)
                        return false;
            return true;
        }

        private int[][] f(int[][] g) {
            int[][] h = new int[3][3];

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    int sum = 0;
                    for (int k = 0; k < 4; k++)
                        if (inBounds(i + dirRow[k], j + dirCol[k]))
                            sum += g[i + dirRow[k]][j + dirCol[k]];
                    h[i][j] = sum % 2;
                }
            return h;
        }

        private boolean inBounds(int i, int j) {
            boolean b = i >= 0 && i < 3 && j >= 0 && j < 3;
            return b;
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

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void close() {
            writer.close();
        }

        public void printLine(int i) {
            writer.println(i);
        }

    }
}

