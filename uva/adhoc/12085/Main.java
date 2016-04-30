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
        Task12085 solver = new Task12085();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task12085 {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int cse = 1;

            while (true) {
                int n = in.nextInt();
                if (n == 0) break;
                out.println("Case " + cse + ":");

                String[] numbers = new String[n];
                for (int i = 0; i < n; i++)
                    numbers[i] = in.next();

                int begin = -1;
                for (int i = 0; i < n; i++) {
                    if (i == 0 || !isConsecutive(numbers, i - 1, i)) {
                        if (i > 0) {
                            out.println(generateRange(numbers, begin, i - 1));
                        }
                        begin = i;
                    }
                }
                out.println(generateRange(numbers, begin, n - 1));
                out.println();
                cse++;
            }
        }

        private boolean isConsecutive(String[] numbers, int prev, int present) {
            int then = Integer.valueOf(numbers[prev]);
            int now = Integer.valueOf(numbers[present]);

            return now - then == 1;
        }

        private String generateRange(String[] numbers, int begin, int end) {
            if (begin == end)
                return numbers[begin];

            int i;
            int l1 = numbers[begin].length();
            int l2 = numbers[end].length();

            for (i = 0; i < Math.min(l1, l2); i++)
                if (numbers[begin].charAt(i) != numbers[end].charAt(i))
                    break;

            String stop = numbers[end].substring(i);
            return numbers[begin] + "-" + stop;
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

