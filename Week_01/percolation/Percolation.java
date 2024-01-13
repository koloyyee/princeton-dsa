/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * if sites are independently set to be open with probability p
 * (and therefore blocked with probability 1 − p)
 * <p>
 * StdRandom??
 * When p equals 0, the system does not percolate;
 * when p equals 1, the system percolates.
 */
/*
 * need a virtual top and bottom
 * */
public class Percolation {

    private WeightedQuickUnionUF wquf;
    private final int n;
    /**
     * vTIdx: virtual Top Index: an index larger than the grid
     * vBIdx: virtual Bottom Index: an index larger than the grid
     * This will be useful when using isFull for checking the connectivity.
     */
    private int vTIdx, vBIdx;
    /**
     * track a 2D grid with a 1D array.
     */
    private boolean[] openTracker;
    private int sitesOpened;

    /**
     * all the elements in grid like "size" in {@WeightedQuickUnionUF}
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        /**
         * grid go from 0 -> n * n - 1
         * vTIdx and vBIdx will be larger than the max
         * */
        this.vTIdx = n * n;
        this.vBIdx = n * n + 1;
        /**
         * the size is larger than the grid by + 2 (vTIdx + 1 + 1)
         * because we can check for connectivity later.
         * */
        wquf = new WeightedQuickUnionUF(vBIdx + 1);
        openTracker = new boolean[n * n];
        sitesOpened = 0;

    }

    // opens the site (row, col) if it is not open already

    /**
     * First, it should validate the indices of the site that it receives.
     * Second, it should somehow mark the site as open.
     * Third, it should perform some sequence of WeightedQuickUnionUF operations
     * that links the site in question to its open neighbors.
     * <p>
     * The constructor and instance variables should facilitate the open() method's ability to do
     * its job.
     */
    public void open(int row, int col) {
        /**
         check if the site is open. check with the openTracker array.
         take the row and col and translate to a 1D array index.
         **/
        validation(row, col);
        // main index
        int idx = xyTo1D(row, col);
        int right = idx + 1;
        int left = idx - 1;
        int above = idx - this.n;
        int beneath = idx + this.n;

        openTracker[idx] = true;
        ++sitesOpened;

        /**
         *how to connect vT and vB top and bottom row?
         */
        if (row == 1) {
            // connecting to the top row.
            wquf.union(idx, vTIdx);
        }
        if (row == this.n) {
            // connecting to the bottom row.
            wquf.union(idx, vBIdx);
        }
        /**how to check for connectivity?
         check for connectivity with surroundings.
         right side
         **/
        if (col < this.n && isOpen(row, col + 1)) {

            wquf.union(idx, right);
        }
        // left side
        if (col > 1 && isOpen(row, col - 1)) {
            wquf.union(idx, left);
        }
        // above side
        if (row > 1 && isOpen(row - 1, col)) {
            wquf.union(idx, above);
        }
        // beneath side
        if (row < this.n && isOpen(row + 1, col)) {
            wquf.union(idx, beneath);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validation(row, col);
        return openTracker[xyTo1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // some site connected to the top row
        // we need virtual top to prevent linear, for-loop
        // use wquf.find(idx1) == wquf.find(idx2);
        validation(row, col);
        return wquf.find(vTIdx) == wquf.find(xyTo1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return sitesOpened;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquf.find(vTIdx) == wquf.find(vBIdx);
        // return false;
    }

    /**
     * Indices validation
     * based on WeightQuickUnionUF.
     */
    private void validation(int row, int col) {

        if (row <= 0 && col <= 0) {
            throw new IllegalArgumentException("row and col cannot be negative.");
        }
        if (row > this.n && col > this.n) {
            throw new IllegalArgumentException(
                    "row and col cannot be larger than " + this.n + ".");
        }
        if (row <= 0) {
            throw new IllegalArgumentException("row cannot be negative.");
        }
        if (row > this.n) {
            throw new IllegalArgumentException("row cannot be larger than " + this.n + ".");
        }
        if (col <= 0) {
            throw new IllegalArgumentException("col cannot be negative.");
        }
        if (col > this.n) {
            throw new IllegalArgumentException("col cannot be larger than " + this.n + ".");
        }

    }

    private int xyTo1D(int row, int col) {
        /** formula n * row + col = index on openTracker.
         * e.g. : 5 * 5 grid
         * (0, 0) = openTracker[0] (Min: 0)
         * (0, 4) = openTracker[4] ((n * row) + col)
         * (1, 0) = openTracker[5] ((n * row) + col)
         * (1, 4) = openTracker[9]((n * row) + col)
         * (2, 4 ) = openTracker[14]((n * row) + col)
         * (3, 2) = openTracker[22]((n * row) + col)
         * (4,4) = openTracker[24] (Max: n * n -1)
         *
         * e.g.: 10 * 10 grid
         * (6, 3) = openTracker[63] ( 10 * 6 + 3)
         * (3, 7) = openTracker[37] ( 10 * 3 + 7)
         * */

        /**
         * this return the index on the openTrack 1D array.
         * offset by - 1 because we have virtual top and bottom included in the grid.
         * row 0 and row n are reserved for virtual top and bottom respectively
         * */
        return this.n * (row - 1) + (col - 1);

    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
