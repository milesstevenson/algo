import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
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
        Task608 solver = new Task608();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task608 {
        List<String> evenCoins;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            while (n-- > 0) {
                String[][] messages = new String[3][3];
                evenCoins = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        messages[i][j] = in.next();
                        if (j == 2 && messages[i][j].equals("even")) {
                            evenCoins.add(messages[i][0]);
                            evenCoins.add(messages[i][1]);
                        }
                    }
                }

                Map<Character, Pair> susMap = new TreeMap<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 2; j++) {
                        for (int k = 0; k < messages[i][j].length(); k++) {
                            char c = messages[i][j].charAt(k);
                            if (!isEvenCoin(c)) {
                                if (susMap.containsKey(c)) {
                                    int count = susMap.get(c).count;
                                    String verdict = susMap.get(c).verdict;
                                    count++;
                                    susMap.put(c, new Pair(count, messages[i][2], j, verdict));
                                } else {
                                    susMap.put(c, new Pair(1, messages[i][2], j, ""));
                                }
                            }
                        }
                    }
                }

                int max = 0;
                char c = 'A';
                for (Character ch : susMap.keySet()) {
                    if (susMap.get(ch).count > max && !susMap.get(ch).verdict.equals("BAD")) {
                        c = ch;
                        max = susMap.get(ch).count;
                    }
                }

                out.println(c + " is the counterfeit coin and it is " + susMap.get(c).verdict + ".");
            }
        }

        private boolean isEvenCoin(char c) {
            for (int i = 0; i < evenCoins.size(); i++) {
                for (int j = 0; j < evenCoins.get(i).length(); j++) {
                    if (evenCoins.get(i).charAt(j) == c) {
                        return true;
                    }
                }
            }
            return false;
        }

        class Pair {
            int count;
            String verdict;
            int side;

            Pair(int count, String verdict, int side, String prev) {
                this.count = count;
                this.side = side;
                String weight = "";
                if (verdict.equals("up")) {
                    weight = side == 0 ? "heavy" : "light";
                }
                if (verdict.equals("down")) {
                    weight = side == 0 ? "light" : "heavy";
                }
                this.verdict = weight;
                if (!prev.isEmpty()) {
                    this.verdict = !prev.equals(this.verdict) ? "BAD" : weight;
                }
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

