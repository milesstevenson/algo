import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
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
		Task161 solver = new Task161();
		solver.solve(1, in, out);
		out.close();
	}

	static class Task161
	{
		private List<Integer> times;
		private List<Integer> lights;
		private final int MINUTE = 60;
		private final int HOUR = 3600;
		private final int FIVE_HOURS = 18000;
		int zero = 0;

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			reset:
			while (true) {
				times = new ArrayList<>();
				lights = new ArrayList<>();
				int m = in.nextInt();
				if (m == 0) {
					zero++;
					if (zero == 3)
						break reset;
					continue reset;
				}


				while (m != 0) {
					lights.add(m);
					times.add(m - 5);
					m = in.nextInt();
					zero = 0;
				}

				Collections.sort(lights);
				Collections.sort(times);
				int s = times.get(0);

				for (int seconds = s; seconds <= FIVE_HOURS; seconds++) {
					if (allLighsGreen(seconds)) {
						String time = getTime(seconds);
						out.println(time);
						continue reset;
					}
				}
				out.println("Signals fail to synchronise in 5 hours");
			}
		}

		private boolean allLighsGreen(int secs) {
			for (int i = 0; i < lights.size(); i++) {
				if (secs % (2 * lights.get(i)) >= times.get(i)) {
					return false;
				}
			}
			return true;
		}

		private String getTime(int seconds) {
			int hours = seconds / HOUR;
			seconds -= (hours * HOUR);
			int minutes = seconds / MINUTE;
			seconds -= (minutes * MINUTE);
			String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
			return time;
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

