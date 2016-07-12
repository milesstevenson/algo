import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
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
        Task1090 solver = new Task1090();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task1090 {
        private long row;
        private long col;
        private long sz;
        private long p;
        private long pos;
        private long sub;
        private long top;
        private long left;
        private long right;
        private long bottom;
        Map<Integer, Integer> map = new HashMap<>();

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            map.put(1, 2);
            map.put(2, 3);
            map.put(3, 4);
            map.put(4, 1);

            while ((sz = in.nextInt()) != 0 && (p = in.nextLong()) != 0) {

                // downsize to find the proper top-right corner
                long z = (long) Math.ceil(Math.sqrt(p));
                pos = z % 2 == 0 ? (z + 1) * (z + 1) : z * z;


            /*
                shrink to the starting position we want, diagonally.

                13|12|11|10|[25]
                14| 3| 2| 9|24            3| 2| [9]
                15| 4| 1| 8|23       ->   4| 1| 8
                16| 5| 6| 7|22            5| 6| 7
                17|18|19|20|21
             */
                long start = (long) Math.sqrt(pos);
                row = 0;
                col = sz - 1;
                int dir = 2; // down

                long dim = sz;
                sub = 0;
                while (dim != start) {
                    row++;
                    col--;
                    dim -= 2;
                    sub++;
                }

                left = sub;
                right = sz - sub;
                top = sub;
                bottom = sz - sub;

                // move in reverse order -- clock-wise until
                // we run into the position we're
                // looking for
                while (pos != p) {
                    dir = move(dir);
                    pos--;
                }

                long x = col + 1, y = sz - row;
                out.println("Line = " + y + ", column = " + x + ".");
            }
        }

        private boolean isOutOfBounds(long r, long c) {
            return r < top || r > bottom - 1 || c < left || c > right - 1;
        }

        private int move(int dir) {
            switch (dir) {
                // up
                case 4:
                    if (isOutOfBounds(row - 1, col)) {
                        dir = move(map.get(dir));
                        left++;
                        //sub += 1;
                    } else
                        row -= 1;
                    break;
                // left
                case 3:
                    if (isOutOfBounds(row, col - 1)) {
                        dir = move(map.get(dir));
                        bottom--;
                    } else
                        col -= 1;
                    break;
                // down
                case 2:
                    if (isOutOfBounds(row + 1, col)) {
                        dir = move(map.get(dir));
                        right--;
                    } else
                        row += 1;
                    break;
                // right
                case 1:
                    if (isOutOfBounds(row, col + 1)) {
                        dir = move(map.get(dir));
                        top++;
                    } else
                        col += 1;
                    break;
            }

            return dir;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

