/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

/***
 * Brute force. Write a program BruteCollinearPoints.java
 * that examines 4 points at a time and
 * checks whether they all lie on the same line segment,
 * returning all such line segments.
 * To check whether the 4 points p, q, r, and s are collinear,
 * check whether the three slopes between p and q,
 * between p and r, and between p and s are all equal.
 * **/
public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null || points.length == 0) throw new IllegalArgumentException();

        Arrays.sort(points);
        lineSegments = new ArrayList<>();

        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) &&
                                points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])) {
                            lineSegments.add(new LineSegment(points[p], points[s]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return lineSegments.size();
    }

    /**
     * The method segments() should include each line segment containing 4 points exactly once.
     * If 4 points appear on a line segment in the order p→q→r→s,
     * then you should include either the line segment p→s or s→p (but not both)
     * and you should not include subsegments such as p→r or q→r.
     * For simplicity, we will not supply any input to BruteCollinearPoints that has 5 or more
     * collinear points.
     **/
    public LineSegment[] segments() {
        // the line segments
        LineSegment[] segments = new LineSegment[lineSegments.size()];
        return lineSegments.toArray(segments);
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}