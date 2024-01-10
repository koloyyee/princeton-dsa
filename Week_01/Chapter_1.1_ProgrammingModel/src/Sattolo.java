import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Random;

/**
 * Sattolo's algorithm
 * generating uniformly distributed cycles of (maximal) length n.
 */
public class  Sattolo{
    public static void main(String[] args) {
            String item = args[0];
            String[] items = item.split("");
            int len = items.length;

            for(int i = len - 1; i >= 0 ; i--) {
              int j = StdRandom.uniformInt(len);
              String temp;
              temp = items[i];
              items[i] = items[j];
                items[j]  = temp;
        System.out.println(Arrays.toString(items));
            }
    }
}
