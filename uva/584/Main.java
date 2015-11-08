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
		Task584 solver = new Task584();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task584 {
    String game;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        while (!(game = in.nextLine()).equals("Game Over")) {
            int score = 0;
            int frame = 1;
            int previous = 0;
            int roll = 0;
            for (int i = 0; i < game.length(); i = nextRoll(i)) {
                if (frame > 10)
                    break;
                if (game.charAt(i) == 'X') {
                    score += 10;
                    score += getStrikeBonus(i, frame);
                    frame++;
                    roll = 0;
                }
                else if (game.charAt(i) == '/') {
                    score += (10 - previous);
                    score += getSpareBonus(i, frame);
                    frame++;
                    roll = 0;
                }
                else {
                    score += (game.charAt(i) - '0');
                    roll++;
                    if (roll == 2) {
                        roll = 0;
                        frame++;
                    }
                }
                previous = game.charAt(i) - '0';
            }
            out.println(score);
        }
    }

    private int getSpareBonus(int i, int frame) {
        int bonus = 0;
        if (frame > 10)
            return 0;
        int next = nextRoll(i);
        char points = game.charAt(next);
        if (points == 'X') {
            bonus = 10;
            return bonus;
        }
        bonus = points - '0';
        return bonus;
    }

    private int getStrikeBonus(int i, int frame) {
        int bonus = 0;
        if (frame > 10)
            return 0;
        int next1 = nextRoll(i);
        char points1 = game.charAt(next1);
        if (points1 == 'X') {
            bonus = 10;
        }
        else {
            bonus = points1 - '0';
        }

        int next2 = nextRoll(next1);
        char points2 = game.charAt(next2);
        if (points2 == 'X')
        {
            bonus += 10;
            return bonus;
        }
        if (points2 == '/')
        {
            int prev = bonus;
            bonus += (10 - prev);
            return bonus;
        }
        bonus += (points2 - '0');
        return bonus;
    }

    private int nextRoll(int i) {
        return i+2;
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

}

