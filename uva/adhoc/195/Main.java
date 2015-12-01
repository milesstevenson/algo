import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
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
		Task195 solver = new Task195();
		solver.solve(1, in, out);
		out.close();
	}

	static class Task195
	{
		String initialOrder = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";

		public void solve(int testNumber, InputReader in, PrintWriter out) {
			int n = in.nextInt();
			for (int i = 0; i < n; i++) {
				char[] word = in.nextLine().toCharArray();
				SortedChar[] sortedWord = new SortedChar[word.length];
				for (int j = 0; j < word.length; j++)
					sortedWord[j] = new SortedChar(word[j]);

				Arrays.sort(sortedWord);
				do {
					String anagram = "";
					for (SortedChar sc : sortedWord)
						anagram = anagram.concat(String.valueOf(sc.ch));
					out.println(anagram);
				} while (hasNextPermutation(sortedWord));
			}
		}

		private boolean hasNextPermutation(SortedChar[] sw) {
			if (sw.length == 1)
				return false;

			int k;
			// find largest k such that sw[k] < sw[k+1]
			for (k = sw.length - 2; k >= 0; k--)
				if (sw[k].compareTo(sw[k + 1]) < 0)
					break;

			if (k == -1)
				return false;

			int l = sw.length - 1;
			// find the largest l such that sw[k] < sw[l]
			while (sw[k].compareTo(sw[l]) >= 0)
				l--;

			// swap the contents of sw[k] and sw[l]
			swap(sw, k, l);

			// reverse the sequence from k+1 to n
			for (int i = k + 1, j = sw.length - 1; i < j; i++, j--)
				swap(sw, i, j);

			return true;
		}

		private void swap(SortedChar[] sw, int i, int j) {
			SortedChar sc = sw[i];
			sw[i] = sw[j];
			sw[j] = sc;
		}

		class SortedChar implements Comparable<SortedChar>
		{
			char ch;

			SortedChar(char x) {
				ch = x;
			}


			public int compareTo(SortedChar s) {
				return initialOrder.indexOf(ch) - initialOrder.indexOf(s.ch);
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

