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
public class Main
{
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task10812 solver = new Task10812();
		solver.solve(1, in, out);
		out.close();
	}

	static class Task10812
	{
		public void solve(int testNumber, InputReader in, PrintWriter out) {
			int cases = in.nextInt();

			while (cases-- > 0) {
				long s = in.nextInt(), d = in.nextInt();
				long a = Math.abs((s - d) / 2), b = (s + d) / 2;
				if (s >= d && (s + d) % 2 == 0) {
					out.println(Math.max(a, b) + " " + Math.min(a, b));
					continue;
				}

				out.println("impossible");
			}
		}

	}

	static class InputReader
	{
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

