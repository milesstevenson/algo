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
		Task11221 solver = new Task11221();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task11221 {
    int k;
    String pal;
    char [][] mat;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int T = in.nextInt();
        int c = 1;
        while (T > 0) {
            out.println("Case #" + c + ":");
            pal = filter(in.nextLine().toUpperCase());
            k = (int) Math.sqrt(pal.length());
            if (k*k != pal.length()) {
                out.println("No magic :(");
                T--;
                c++;
                continue;
            }
            mat = new char[k][k];

            for (int i = 0; i < k; i++)
                for (int j = 0; j < k; j++)
                    mat[i][j] = pal.charAt((i*k) + j);

            boolean one = way1();
            boolean two = way2();
            boolean three = way3();
            boolean four = way4();

            if (one && two && three && four)
                out.println(k);
            else
                out.println("No magic :(");
            T--;
            c++;
        }
    }

    private boolean way4() {
        int pos = 0;
        for (int i = k-1; i >= 0; i--)
            for (int j = k-1; j >= 0; j--) {
                if (mat[j][i] != pal.charAt(pos))
                    return false;
                pos++;
            }
        return true;
    }

    private boolean way3() {
        int pos = 0;
        for (int i = k-1; i >= 0; i--)
            for (int j = k-1; j >= 0; j--) {
                if (mat[i][j] != pal.charAt(pos))
                    return false;
                pos++;
        }
        return true;
    }

    private boolean way2() {
        int pos = 0;
        for (int i = 0; i < k; i++)
            for (int j = 0; j < k; j++) {
                if (mat[j][i] != pal.charAt(pos))
                    return false;
                pos++;
            }
        return true;
    }

    private boolean way1() {
        int pos = 0;
        for (int i = 0; i < k; i++)
            for (int j = 0; j < k; j++) {
                if (mat[i][j] != pal.charAt(pos))
                    return false;
                pos++;
            }
        return true;
    }

    private String filter(String pal) {
        String f = "";
        for (int i = 0; i < pal.length(); i++) {
            char ch = pal.charAt(i);
            if (ch >= 'A'&& ch <= 'Z') {
                f = f.concat(Character.toString(ch));
            }
        }
        return f;
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

