import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] grid;
    private final WeightedQuickUnionUF uf;
    private final int[] adjRow = {-1, 0, 1, 0};
    private final int[] adjCol = {0, 1, 0,-1};
    private final int N;

    /**
     * Creates an N-by-N grid, with all sites blocked
     */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("Bad data found");
        this.N = N;
        uf = new WeightedQuickUnionUF(N*N+2);
        grid = new boolean[N+1][N+1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                grid[i][j] = false;
            }
        }
    }

    /**
     * Open site (row i, column j), if it's not already open
     */
    public void open(int i, int j) {
        if (isOutOfBounds(i) || isOutOfBounds(j))
            throw new IndexOutOfBoundsException("Not in bounds");
        if (!isOpen(i,j)) {
            grid[i][j] = true;
            connectAdjacentSites(i,j);
        }
    }

    /**
     * Connect site (row i, column j) to all adjacent open sites.
     */
    private void connectAdjacentSites(int i, int j) {
        int arow, acol;
        for (int k = 0; k < 4; k++) {
            arow = i + adjRow[k]; acol = i + adjCol[k];
            if (!isOutOfBounds(arow) && !isOutOfBounds(acol)) {
                if (isOpen(arow,acol)) {
                    int p = getUfId(i,j);
                    int q = getUfId(arow,acol);
                    uf.union(p,q);
                }
            }
        }
        int p = getUfId(i,j);
        if (i == 1)
            uf.union(p,N*N); // virtual top
        if (i == N)
            uf.union(p,N*N+1); // virtual bottom
    }

    /**
     * Get the id that corresponds both to the local matrix
     * of this object and the site in UF.
     */
    private int getUfId(int i, int j) {
        return ((i-1) * N) + j-1;
    }

    /**
     * Site might be out of bounds.
     */
    private boolean isOutOfBounds(int i) {
        return (i < 1 || i > N);
    }

    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        if (isOutOfBounds(i) || isOutOfBounds(j))
            throw new IndexOutOfBoundsException("Not in bounds");
        return grid[i][j] != false;
    }

    /**
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (isOutOfBounds(i) || isOutOfBounds(j))
            throw new IndexOutOfBoundsException("Not in bounds");
        if (!isOpen(i,j))
            return false;
        int p = getUfId(i, j);
        return uf.connected(p, N*N);
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return uf.connected(N*N,N*N+1);
    }

    public static void main(String[] args)  {
        // TODO
    }
}
