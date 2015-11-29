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
		Task156 solver = new Task156();
		solver.solve(1, in, out);
		out.close();
	}

	static class Task156
	{
		public void solve(int testNumber, InputReader in, PrintWriter out) {
			String word;
			Map<String, ArrayList<String>> map = new HashMap<>();
			while (!(word = in.next()).equals("#")) {
				char[] charArray = word.toLowerCase().toCharArray();
				Arrays.sort(charArray);
				String sorted = Arrays.toString(charArray);

				if (!map.containsKey(sorted)) {
					map.put(sorted, new ArrayList<String>());
				}

				map.get(sorted).add(word);
			}

			List<String> ananagrams = new ArrayList<>();
			for (List<String> list : map.values()) {
				if (list.size() == 1) {
					ananagrams.add(list.get(0));
				}
			}
			Collections.sort(ananagrams);
			for (String a : ananagrams) {
				out.println(a);
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

	}
}

