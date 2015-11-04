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
 * @author Miles Stevenson (miles.d.stevenson@gmail.com)
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task11459 solver = new Task11459();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task11459 {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int cases = in.nextInt();
            int a, b, c;
            int mouth, tail;
            /**
             * If the token is on a square containing the borrom of a ladder, the token must be moved to the square
             * containing the top of the ladder.
             *
             * Similarly, if the token is on a square containing the mouth of a snake, the token must be moved to the square
             * containing the tail of the snake.
             *
             * A player wins when his or her token reached the square numbered 100. At that point, the game ends.
             *
             * The sequence of die rolls may also continue after the game is over; in this case, the die rolls after the end
             * of the game should be ignored.
             */
            while (cases > 0) {
                /**
                 * The first line contains three positive integers: the number a of players, the number b of snakes or
                 * ladders, and the number c of die rolls.
                 */
                a = in.nextInt();
                b = in.nextInt();
                c = in.nextInt();
                Map<Integer, Integer> players = new HashMap<>();
                for (int i = 0; i < a; i++) {
                    players.put(i, 1);
                }
                /**
                 * Each of the next b lines contains two integers specifying a snake or ladder.
                 * The first integer indicates the square containing the mouth of the snake or botom of the ladder.
                 * The second integer indicates the square containing the tail of the snake or the top of the ladder.
                 */
                Map<Integer, Integer> snakes = new HashMap<>();
                for (int i = 0; i < b; i++) {
                    mouth = in.nextInt();
                    tail = in.nextInt();
                    snakes.put(mouth, tail);
                }

                /**
                 * The following c lines each contain one integer giving the number rolled on the die
                 */
                boolean winner = false;
                for (int i = 0; i < c; i++) {
                    if (winner) {
                        in.nextInt();
                        continue;
                    }
                    int player = i % a;
                    int die = in.nextInt();

                    int currentPosition = players.get(player);
                    int nextPosition = currentPosition + die;
                    if (snakes.containsKey(nextPosition)) {
                        nextPosition = snakes.get(nextPosition);
                    }
                    players.put(player, nextPosition);
                    if (nextPosition == 100) {
                        winner = true;
                        continue;
                    }
                }

                for (int i = 0; i < a; i++) {
                    out.println("Position of player " + (i + 1) + " is " + players.get(i) + ".");
                }
                cases--;
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

