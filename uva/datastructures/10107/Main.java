import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.Vector;
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
        Task10107 solver = new Task10107();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task10107 {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String X;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            Stack<Integer> stack = new Stack<>();

            while ((X = in.next()) != null) {
                int x = Integer.valueOf(X);
                pq.add(x);
                int sz = pq.size();
                int median = 0;

                if (sz == 1) {
                    median = pq.peek();
                } else {
                    for (int i = 0; i <= sz / 2; i++) {
                        stack.add(pq.poll());
                        if ((i == (sz / 2) - 1 && sz % 2 == 0) || i == (sz / 2)) {
                            median += stack.peek();
                        }
                    }
                    if (sz % 2 == 0)
                        median /= 2;
                }

                while (!stack.isEmpty())
                    pq.add(stack.pop());
                out.printLine(median);
            }
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

