import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.util.Collections;
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
        Task10141 solver = new Task10141();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task10141 {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int rfp = 1;
            while (true) {
                int n = in.nextInt();
                int p = in.nextInt();
                if (n == 0 && p == 0)
                    break;
                if (rfp > 1)
                    out.println();
                out.println("RFP #" + rfp);

                for (int i = 0; i < n; i++)
                    in.nextLine();

                Map<Integer, List<RFP>> map = new TreeMap<>(Collections.reverseOrder());
                for (int i = 0; i < p; i++) {
                    String proposal = in.nextLine();
                    double cost = in.nextDouble();
                    int metRequirements = in.nextInt();

                    if (!map.containsKey(metRequirements))
                        map.put(metRequirements, new ArrayList<RFP>());
                    map.get(metRequirements).add(new RFP(proposal, cost));

                    for (int j = 0; j < metRequirements; j++)
                        in.nextLine();
                }

                Iterator it = map.entrySet().iterator();
                Map.Entry item = (Map.Entry) it.next();
                List<RFP> list = (List<RFP>) item.getValue();

                if (list.size() > 1) {
                    String proposal = "";
                    double cost = Double.MAX_VALUE;
                    for (int i = 0; i < list.size(); i++) {
                        double prev = cost;
                        cost = Math.min(cost, list.get(i).cost);
                        if (cost != prev) {
                            proposal = list.get(i).proposal;
                        }
                    }
                    out.println(proposal);
                } else {
                    out.println(list.get(0).proposal);
                }
                rfp++;
            }
        }

        class RFP {
            String proposal;
            double cost;

            public RFP(String p, double c) {
                proposal = p;
                cost = c;
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

        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }
}

