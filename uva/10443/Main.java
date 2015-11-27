import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Miles Stevenson
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task10443 solver = new Task10443();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task10443 {
    char[][] grid;
    char[][] tGrid;
    int r, c, n;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int t = in.nextInt();
        boolean first = true;
        while ((t--) > 0) {
            if (!first)
                out.println();
            first = false;
            
            r = in.nextInt();
            c = in.nextInt();
            n = in.nextInt();

            grid = new char[r][c];
            tGrid = new char[r][c];

            for (int i = 0; i < r; i++) {
                String row = in.nextLine();
                for (int j = 0; j < c; j++) {
                    grid[i][j] = row.charAt(j);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int row = 0; row < r; row++) {
                    for (int col = 0; col < c; col++) {
                        if (grid[row][col] == 'R') {
                            check(row, col, 'P');
                        }
                        else if (grid[row][col] == 'S') {
                            check(row, col, 'R');
                        }
                        else {
                            check(row, col, 'S');
                        }
                    }
                }
                replace();
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    out.print(tGrid[i][j]);
                }
                out.println();
            }

        }
    }

    private void replace() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = tGrid[i][j];
            }
        }
    }

    private void check(int row, int col, char takeover) {
        int cRow = row;
        int cCol = col;

        cRow = row-1;
        cCol = col;
        if (cRow > -1 && grid[cRow][cCol] == takeover) {
            tGrid[row][col] = takeover;
            return;
        }

        cRow = row;
        cCol = col+1;
        if (cCol < c && grid[cRow][cCol] == takeover) {
            tGrid[row][col] = takeover;
            return;
        }

        cRow = row+1;
        cCol = col;
        if (cRow < r && grid[cRow][cCol] == takeover) {
            tGrid[row][col] = takeover;
            return;
        }

        cRow = row;
        cCol = col-1;
        if (cCol > -1 && grid[cRow][cCol] == takeover) {
            tGrid[row][col] = takeover;
            return;
        }

        tGrid[row][col] = grid[row][col];
    }
}

class InputReader {
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

