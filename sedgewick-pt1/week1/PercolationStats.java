
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] a;
    private final int T;

    /**
     * Perform T independent experiments on an N-by-N grid
     *
     * @param N number of sites.
     * @param T number of experiments.
     */
    public PercolationStats(int N, int T) {
        if (T <= 0 || N <= 0)
            throw new IllegalArgumentException("Improper argmument found");

        a = new double[T];
        this.T = T;
        final double denominator = N*N;

        for (int i = 0; i < this.T; i++) {
            double t = 0;
            Percolation perc = new Percolation(N);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N) + 1;
                int col = StdRandom.uniform(N) + 1;
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    t++;
                }
            }
            a[i] = t/denominator;
        }

    }

    /**
     * Sample mean of percolation threshold.
     *
     * @return sample mean
     */
    public double mean()
    {
        return StdStats.mean(a);
    }

    /**
     * Sample standard deviation of percolation threshold
     *
     * @return
     */
    public double stddev()
    {
        return StdStats.stddev(a);
    }

    /**
     * Low endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceLo() {
        double m = mean();
        double d = Math.sqrt(T);
        double n = 1.96 * stddev();
        return m - (n/d);
    }

    /**
     * High endpoint of 95% confidence interval
     *
     * @return
     */
    public double confidenceHi() {
        double m = mean();
        double d = Math.sqrt(T);
        double n = 1.96 * stddev();
        return m + (n/d);
    }

    public static void main(String[] args) {
        int n = 200;//Integer.parseInt(args[0]);
        int t = 100;//Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, t);
        double m = stats.mean();
        double s = stats.stddev();
        double lo = stats.confidenceLo();
        double hi = stats.confidenceHi();

        StdOut.println("mean                    = " + m);
        StdOut.println("stddev                  = " + s);
        StdOut.println("95% confidence interval = " + lo + ", " + hi);
    }
}
