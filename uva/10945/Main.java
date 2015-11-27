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
		Task10945 solver = new Task10945();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task10945 {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        String str;
        while (!(str = in.nextLine()).equals("DONE")) {
            String filteredStr = filter(str);
            boolean isPal = isPalindrome(filteredStr);
            if (isPal)
                out.println("You won't be eaten!");
            else
                out.println("Uh oh..");
        }
    }


    private boolean isPalindrome(String str) {
        for (int i = 0, j = str.length()-1; i <= j; i++, j--) {
            if (str.charAt(i) != str.charAt(j))
                return false;
        }
        return true;
    }


    private String filter(String str) {
        String fil = "";
        for (int i = 0; i < str.length(); i++) {
            Character cur = str.charAt(i);
            if (Character.isLetter(cur)) {
                fil = fil.concat(cur.toString());
            }
        }
        return fil.toUpperCase();
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

