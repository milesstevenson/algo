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
		Task637 solver = new Task637();
		solver.solve(1, in, out);
		out.close();
	}

	static class Task637
	{
		public void solve(int testNumber, InputReader in, PrintWriter out) {
			int c;
			int right = 1, left = 0;
			while ((c = in.nextInt()) != 0) {
				if (c == 1) {
					out.println("Printing order for " + c + " pages:");
					out.println("Sheet 1, front: Blank, 1");
					continue;
				}
				int sheets = (c % 4 == 0) ? c / 4 : (c / 4) + 1;

				int a[][] = new int[2 * sheets][2];

				// down
				int side, count, i;
				for (count = 1, i = 0; i < 2 * sheets; i += 2) {
					side = right;

					if (count > c) {
						a[i][side] = 0;
					} else {
						a[i][side] = count;
					}

					side = left;
					count++;

					if (count > c) {
						a[i + 1][side] = 0;
					} else {
						a[i + 1][side] = count;
					}

					count++;
				}

				// up
				for (i = 2 * sheets - 1; i > 0; i -= 2) {
					side = right;

					if (count > c) {
						a[i][side] = 0;
					} else {
						a[i][side] = count;
					}

					side = left;
					count++;

					if (count > c) {
						a[i - 1][side] = 0;
					} else {

						a[i - 1][side] = count;
					}

					count++;
				}
//
//            for (int j = 0; j < 2*sheets; j++) {
//                for (int k = 0; k < 2; k++) {
//                    out.print(a[j][k] + " ");
//                }
//                out.println();
//            }
//            out.println();

				out.println("Printing order for " + c + " pages:");
				int sheet = 1;
				for (int j = 0; j < 2 * sheets; j += 2, sheet++) {
					out.println("Sheet " + sheet + ", front: " + num(a, j));
					out.println("Sheet " + sheet + ", back : " + num(a, j + 1));
				}
			}
		}

		private String num(int[][] a, int row) {
			String first = (a[row][0] == 0) ? "Blank" : String.valueOf(a[row][0]);
			String second = (a[row][1] == 0) ? "Blank" : String.valueOf(a[row][1]);
			return first + ", " + second;
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

