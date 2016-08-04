import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Miles Stevenson
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Task10258 solver = new Task10258();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task10258 {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int cases = in.nextInt();
            // have to get rid of the empty line between the first case
            String line = in.nextLine();

            while (cases > 0) {
                Contestant[] contestant = new Contestant[101];
                for (int i = 0; i <= 100; i++) {
                    contestant[i] = new Contestant(i);
                }

                line = in.nextLine();

                while (null != line && !line.isEmpty()) {
                    String[] submission = line.split(" ");
                    line = in.nextLine();

                    int id = Integer.parseInt(submission[0]);
                    contestant[id].submit(submission);
                }

                Arrays.sort(contestant);
                for (int i = 0; i <= 100; i++) {
                    if (contestant[i].submitted) {
                        out.printLine(contestant[i].id + " " + contestant[i].problemsSolved
                                + " " + contestant[i].penaltyTime);
                    }
                }
                cases--;
                if (cases > 0) {
                    out.printLine();
                }
            }
        }

    }

    static class Contestant implements Comparable<Contestant> {
        int id;
        boolean submitted;
        boolean[] solved = new boolean[10];
        int[] wrongSubmissions = new int[10];
        int problemsSolved;
        int penaltyTime;

        Contestant(int id) {
            this.id = id;
            submitted = false;
            problemsSolved = 0;
            penaltyTime = 0;

            Arrays.fill(solved, false);
            Arrays.fill(wrongSubmissions, 0);
        }

        public void submit(String[] submission) {
            int problem = Integer.parseInt(submission[1]);
            int time = Integer.parseInt(submission[2]);
            char verdict = submission[3].charAt(0);
            submitted = true;

            if (!solved[problem]) {
                if (verdict == 'C') {
                    solved[problem] = true;
                    penaltyTime += wrongSubmissions[problem] * 20 + time;
                    problemsSolved++;
                } else if (verdict == 'I') {
                    wrongSubmissions[problem]++;
                }
            }

        }


        public int compareTo(Contestant that) {
            if (this.problemsSolved != that.problemsSolved)
                return that.problemsSolved - this.problemsSolved;
            if (this.penaltyTime != that.penaltyTime)
                return this.penaltyTime - that.penaltyTime;
            return this.id - that.id;
        }

    }

    static class InputReader {
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

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void printLine() {
            writer.println();
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }
}

