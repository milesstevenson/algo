import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.BufferedReader;
import java.util.Map;
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
		Task401 solver = new Task401();
		solver.solve(1, in, out);
		out.close();
	}
}

class Task401 {
    private Map<Character, Character> mirror = new HashMap<>();
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        precomputeMirrors();
        String original;
        while (null != (original = in.nextLine())){
            String s = original;
            boolean palindrome = isPalindrome(s);
            boolean mirrored = isMirrored(s);

            if (palindrome && mirrored) {
                out.println(original + " -- is a mirrored palindrome.");
                out.println();
            }
            else if (!palindrome && mirrored) {
                out.println(original + " -- is a mirrored string.");
                out.println();
            }
            else if (palindrome && !mirrored) {
                out.println(original + " -- is a regular palindrome.");
                out.println();
            }
            else {
                out.println(original + " -- is not a palindrome.");
                out.println();
            }
        }
    }

    private boolean isMirrored(String s) {
        for (int i = 0, j = s.length()-1; i <= j; i++, j--) {
            Character left = s.charAt(i);
            Character revLeft = mirror.get(left);
            Character right = s.charAt(j);
            Character revRight = mirror.get(right);
            if (null == revLeft || null == revRight) {
                return false;
            }
            if (revLeft != right) {
                return false;
            }
            if (left != revRight) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length()-1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }

    private void precomputeMirrors() {
        mirror.put('A', 'A');
        mirror.put('E', '3');
        mirror.put('H', 'H');
        mirror.put('I', 'I'); mirror.put('J', 'L');
        mirror.put('L', 'J');
        mirror.put('M', 'M');
        mirror.put('O', 'O');
        mirror.put('S', '2');
        mirror.put('T', 'T'); mirror.put('U', 'U');
        mirror.put('V', 'V'); mirror.put('W', 'W');
        mirror.put('X', 'X'); mirror.put('Y', 'Y');
        mirror.put('Z', '5'); mirror.put('1', '1');
        mirror.put('2', 'S'); mirror.put('3', 'E');
        mirror.put('5', 'Z');
        mirror.put('8', '8');
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

