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
        Task11947 solver = new Task11947();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task11947 {
        int JAN = GregorianCalendar.JANUARY;
        int FEB = GregorianCalendar.FEBRUARY;
        int MAR = GregorianCalendar.MARCH;
        int APR = GregorianCalendar.APRIL;
        int MAY = GregorianCalendar.MAY;
        int JUN = GregorianCalendar.JUNE;
        int JUL = GregorianCalendar.JULY;
        int AUG = GregorianCalendar.AUGUST;
        int SEP = GregorianCalendar.SEPTEMBER;
        int OCT = GregorianCalendar.OCTOBER;
        int NOV = GregorianCalendar.NOVEMBER;
        int DEC = GregorianCalendar.DECEMBER;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int runs = 1;
            while (n-- > 0) {
                int date = in.nextInt();
                int year = date % 10000;
                date /= 10000;
                int day = date % 100;
                date /= 100;
                int month = date - 1;

                GregorianCalendar cal = new GregorianCalendar(year, month, day);
                int forty_weeks = 7 * 40;

                cal.add(GregorianCalendar.DAY_OF_YEAR, forty_weeks);

                int M = cal.get(GregorianCalendar.MONTH) + 1;
                int D = cal.get(GregorianCalendar.DAY_OF_MONTH);
                int Y = cal.get(GregorianCalendar.YEAR);

                out.printf("%d %02d/%02d/%04d %s\n", runs, M, D, Y, getZodiac(cal));
                runs++;
            }
        }

        private String getZodiac(GregorianCalendar c) {
            int month = c.get(GregorianCalendar.MONTH);
            int day = c.get(GregorianCalendar.DAY_OF_MONTH);

            if (between(month, day, JAN, 21, FEB, 19)) {
                return "aquarius";
            } else if (between(month, day, FEB, 20, MAR, 20)) {
                return "pisces";
            } else if (between(month, day, MAR, 21, APR, 20)) {
                return "aries";
            } else if (between(month, day, APR, 21, MAY, 21)) {
                return "taurus";
            } else if (between(month, day, MAY, 22, JUN, 21)) {
                return "gemini";
            } else if (between(month, day, JUN, 22, JUL, 22)) {
                return "cancer";
            } else if (between(month, day, JUL, 23, AUG, 21)) {
                return "leo";
            } else if (between(month, day, AUG, 22, SEP, 23)) {
                return "virgo";
            } else if (between(month, day, SEP, 24, OCT, 23)) {
                return "libra";
            } else if (between(month, day, OCT, 24, NOV, 22)) {
                return "scorpio";
            } else if (between(month, day, NOV, 23, DEC, 22)) {
                return "sagittarius";
            } else {
                return "capricorn";
            }
        }

        private boolean between(int month, int day, int beginMonth, int beginDay,
                                int endMonth, int endDay) {
            boolean b = ((month == beginMonth && day >= beginDay)
                    || (month == endMonth && day <= endDay));
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
}

