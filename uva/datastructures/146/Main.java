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
        Task146 solver = new Task146();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task146 {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            String s;
            while (!(s = in.next()).equals("#")) {
                out.printLine(next_permutation(s));
            }
        }

        private String next_permutation(String s) {
            char[] a = s.toCharArray();

            int len = s.length();
            boolean exists = false;
            int k = 0;
            int l = 0;

            // Find the largest index k such that a[k] < a[k+1]
            for (int i = 1; i < len; i++) {
                if (a[i - 1] < a[i]) {
                    k = i - 1;
                    exists = true;
                }
            }

            // If no such index exists, the permutation is the last permutation
            if (!exists)
                return "No Successor";

            // Find the largest index l greater than k such that a[k] < a[l]
            for (int i = k + 1; i < len; i++) {
                if (a[k] < a[i])
                    l = i;
            }

            // Swap the value of a[k] with that of a[l]
            swap(a, k, l);

            int i = k + 1;
            int j = len - 1;

            // Reverse the sequence from a[k+1] up to and including the final
            // element a[n]
            while (i < j) {
                swap(a, i, j);
                i++;
                j--;
            }

            return String.valueOf(a);
        }

        private void swap(char[] a, int i, int j) {
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
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

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }
}

