/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<>();
        final int size = Integer.parseInt(args[0]);
        if (size < 0) {
            throw new IllegalArgumentException(
                    "cannot be negative or larger than the input lines amount");
        }
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            r.enqueue(input);
        }

        for (int i = 0; i < size; i++) {
            StdOut.println(r.dequeue());
        }

    }

}
