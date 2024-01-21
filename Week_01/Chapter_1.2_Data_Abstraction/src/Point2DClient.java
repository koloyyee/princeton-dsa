import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class Point2DClient {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        // create points
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            int x = StdRandom.uniformInt(n);
            int y = StdRandom.uniformInt(n);

            points[i] = new Point2D(x , y);
        }

        int maxLen = 0;
        Point2D p = new Point2D(0, 0 );
        for (int i = 0; i < n -1; i++) {
            Point2D p1 = points[i];
            Point2D p2 = points[i + 1];

            int len = p1.compareTo(p2);
            if(maxLen < len) {
                maxLen = len;
                p = p1;
            }
        }
        System.out.println(p);
    }
}
