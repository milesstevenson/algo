import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
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
        Task893 solver = new Task893();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task893 {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String sahead, sday, smonth, syear;
            while (null != (sahead = in.next())) {
                sday = in.next();
                smonth = in.next();
                syear = in.next();

                int ahead = Integer.parseInt(sahead);
                int day = Integer.parseInt(sday);
                int month = Integer.parseInt(smonth);
                int year = Integer.parseInt(syear);

                if (ahead == 0 && day == 0 && month == 0 && year == 0)
                    break;

                GregorianCalendar cal = new GregorianCalendar(year, month - 1, day);
                cal.add(GregorianCalendar.DAY_OF_YEAR, ahead);

                out.println(cal.get(GregorianCalendar.DAY_OF_MONTH) + " " + (cal.get(GregorianCalendar.MONTH) + 1) + " " + cal.get(GregorianCalendar.YEAR));
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
}

