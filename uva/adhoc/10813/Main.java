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
		Task10813 solver = new Task10813();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task10813 {
    private final int FREE = 2;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int games = in.nextInt();
        int current = 0;
        while (current < games)
        {
int[][] boardValues = new int[5][5];
            boolean[][] boardTruth = new boolean[5][5];

            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (row == FREE && col == FREE) {
                        boardTruth[row][col] = true;
                        boardValues[row][col] = -1;
                        continue;
                    }
                    boardTruth[row][col] = false;
                    boardValues[row][col] = in.nextInt();
                }
            }
            int firstOccurance = 0;
            boolean occuranceFound = false;

            for (int i = 0; i < 75; i++) {
                playNumber(in.nextInt(), boardTruth, boardValues);
                if (!occuranceFound && isBingo(boardTruth)) {
                    firstOccurance = i+1;
                    occuranceFound = true;
                }
            }
            out.println("BINGO after " + firstOccurance + " numbers announced");
            current++;
        }
    }

    private void playNumber(int value, boolean[][] boardTruth, int[][] boardValues) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (boardValues[row][col] == value) {
                    boardTruth[row][col] = true;
                    return;
                }
            }
        }
    }
    private boolean isBingo(boolean[][] boardTruth) {
        return isBingoRow(boardTruth) || isBingoCol(boardTruth) || isBingoDiag(boardTruth);
    }

    private boolean isBingoRow(boolean[][] boardTruths) {
        for (int row = 0; row < 5; row++) {
            boolean truthRow = true;
            for (int col = 0; col < 5; col++) {
                truthRow &= boardTruths[row][col];
            }
            if (truthRow) {
                return true;
            }
        }
        return false;
    }

    private boolean isBingoCol(final boolean[][] boardTruth) {
        for (int col = 0; col < 5; col++) {
            boolean truthCol = true;
            for (int row = 0; row < 5; row++) {
                truthCol &= boardTruth[row][col];
            }
            if (truthCol) {
                return true;
            }
        }

        return false;
    }

    private boolean isBingoDiag(final boolean[][] boardTruth) {
        boolean truthDiagDown = boardTruth[0][0] && boardTruth[1][1]
                                && boardTruth[2][2] && boardTruth[3][3]
                                && boardTruth[4][4];
        boolean truthDiagUp = boardTruth[4][0] && boardTruth[3][1]
                              && boardTruth[2][2] && boardTruth[1][3]
                              && boardTruth[0][4];
        return truthDiagUp || truthDiagDown;
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

