/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

/**
 *  if sites are independently set to be open with probability p
 *  (and therefore blocked with probability 1 − p)
 *
 *  StdRandom??
 *   When p equals 0, the system does not percolate;
 *   when p equals 1, the system percolates.
* */
/*
* need a virtual top and bottom
* */
public class Percolation {
    private WeightedQuickUnionUF wqu;
    int vT, vB; // the two points on top and bottom of the grid
    /**
     * all the elements in grid like "size" in {@WeightedQuickUnionUF}
    * */
    boolean[] opened; //
    public Percolation(int n) {
        int vT = n * n;
        int vB = n * n + 1;
        wqu = new WeightedQuickUnionUF(vB + 1);
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if(isOpen(row, col)) {
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {

        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 0;
    }

    // does the system percolate?
    public boolean percolates() {
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
