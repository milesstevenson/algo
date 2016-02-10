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
        Task556 solver = new Task556();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task556 {
        int[][] maze;
        int row;
        int col;
        Coordinate EAST = new Coordinate(1, 0);
        Coordinate WEST = new Coordinate(-1, 0);
        Coordinate NORTH = new Coordinate(0, -1);
        Coordinate SOUTH = new Coordinate(0, 1);

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            while ((row = in.nextInt()) != 0 && (col = in.nextInt()) != 0) {

                if (row == 1 && col == 1) {
                    out.println("  1  0  0  0  0");
                }
                maze = new int[row][col];
                for (int i = 0; i < row; i++) {
                    String mazeRow = in.nextLine();
                    for (int j = 0; j < col; j++)
                        maze[i][j] = (mazeRow.charAt(j) - '0') == 1 ? -1 : 0;
                }

                Coordinate end = new Coordinate(0, row - 1);
                Coordinate direction = EAST;
                Coordinate previous = end;
                Coordinate current;

                do {
                    current = previous.add(direction);
                    if (outOfBounds(current.x, current.y) || maze[current.y][current.x] == -1) {
                        direction = turnLeft(direction);
                    } else {
                        ++maze[current.y][current.x];
                        direction = turnRight(direction);
                        previous = current;
                    }
                } while (current.x != end.x || current.y != end.y);

                int arr[] = {0, 0, 0, 0, 0};
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (maze[i][j] > -1 && maze[i][j] < 5)
                            arr[maze[i][j]]++;
                    }
                }

                out.printf("%3d%3d%3d%3d%3d\n", arr[0], arr[1], arr[2], arr[3], arr[4]);
            }
        }

        private Coordinate turnLeft(Coordinate c) {
            if (c == EAST)
                return NORTH;
            else if (c == NORTH)
                return WEST;
            else if (c == WEST)
                return SOUTH;
            else
                return EAST;
        }

        private Coordinate turnRight(Coordinate c) {
            if (c == EAST)
                return SOUTH;
            else if (c == SOUTH)
                return WEST;
            else if (c == WEST)
                return NORTH;
            else
                return EAST;
        }

        private boolean outOfBounds(int x, int y) {
            return x < 0 || y < 0 || x >= col || y >= row;
        }

        private class Coordinate {
            int x;
            int y;

            public Coordinate(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public Coordinate add(Coordinate c) {
                return new Coordinate(x + c.x, y + c.y);
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

