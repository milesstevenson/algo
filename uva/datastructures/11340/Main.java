import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.Map;
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
        PrintWriter out = new PrintWriter(outputStream);
        Task11340 solver = new Task11340();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task11340 {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] alpha = new int[26];
            while (n-- > 0) {
                Map<Character, Integer> map = new HashMap<>();
                int k = in.nextInt();
                for (int i = 0; i < k; i++) {
                    char ch = in.next().charAt(0);
                    int price = in.nextInt();
                    map.put(ch, price);
                }
                double cost = 0;
                int m = in.nextInt();
                for (int i = 0; i < m; i++) {
                    String line = in.nextLine();
                    for (int j = 0; j < line.length(); j++) {
                        if (map.containsKey(line.charAt(j))) {
                            cost += map.get(line.charAt(j));
                        }
                    }
                }
                cost /= 100;
                NumberFormat formatter = new DecimalFormat("#0.00");
                out.println(formatter.format(cost) + "$");
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

