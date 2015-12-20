import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
import java.util.Collections;
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
		Task454 solver = new Task454();
		solver.solve(1, in, out);
		out.close();
	}

	static class Task454
	{
		public void solve(int testNumber, InputReader in, PrintWriter out) {
			int N = in.nextInt();
			int count = 0;
			in.nextLine();
			while (count < N) {
				Map<String, String> M = new HashMap<>();
				List<String> V = new ArrayList<>();
				String s;

				if (count > 0)
					out.println();

				while (null != (s = in.nextLine()) && !s.isEmpty()) {
					V.add(s);
					M.put(s, rem(s));
				}

				Collections.sort(V);
				for (int i = 0; i < V.size(); i++) {
					for (int j = i + 1; j < V.size(); j++) {
						if (M.get(V.get(i)).equals(M.get(V.get(j)))) {
							out.println(V.get(i) + " = " + V.get(j));
						}
					}
				}
				count++;
			}
		}

		private String rem(String s) {
			String t = "";
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) != ' ')
					t = t.concat(String.valueOf(s.charAt(i)));
			}
			char[] t_array = t.toCharArray();
			Arrays.sort(t_array);
			t = String.valueOf(t_array);
			return t;
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

