/* *****************************************************************************
 *  Name: Loyyee Ko
 *  Date: 05/02/2024
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {
    private LinkedList<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null) throw new IllegalArgumentException();

        lineSegments = new LinkedList<>();

        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        int size = points.length;

        // 1. walk through the points, start with the smallest: p
        for (int i = 0; i < size; i++) {
            Point p = sortedPoints[i];

            Point[] sortBySlope = sortedPoints.clone();
            // 2. sort by the slope order
            Arrays.sort(sortBySlope, p.slopeOrder());

            // p is 0, offset by 1
            int j = 1;
            while (j < size) {
                LinkedList<Point> collinearCheck = new LinkedList<>();
                // next to check
                double slopeToCheck = p.slopeTo(sortBySlope[j]);
                // we are checking all the points has the same slope p -> j = sortBySlop(j)
                // do while because we ensure j will be in since we p, j will a line.
                do {
                    // we add first then j += 1
                    collinearCheck.add(sortBySlope[j++]);
                } while (j < size && p.slopeTo(sortBySlope[j]) == slopeToCheck);

                // If more than 3 points have been added, means there's at least 4 collinear points
                if (collinearCheck.size() >= 3 && p.compareTo(collinearCheck.getFirst()) < 0) {
                    // smallest in the linked list, therefore it is the first
                    Point first = p;
                    Point last = collinearCheck.getLast();
                    lineSegments.add(new LineSegment(first, last));
                }
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.size();
    }

    /**
     * The method segments() should include each maximal line segment
     * containing 4 (or more) points exactly once.
     * For example, if 5 points appear on a line segment in the order p→q→r→s→t,
     * then do not include the subsegments p→s or q→t.
     */
    public LineSegment[] segments() {
        // the line segments
        LineSegment[] segements = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(segements);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}