import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {

        String champ = "";
        int count = 0;
        while (!StdIn.isEmpty()) {
            count++;
            if (StdRandom.bernoulli(1.0 / count)) {
                champ = StdIn.readString();
            }
        }
        StdOut.println(champ);
    }
}
