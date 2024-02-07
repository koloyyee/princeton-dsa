package own;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Insertion {
    private Insertion(){}

    public static <T extends Comparable<T>> void sort(Comparable[] a) {
       int n = a.length;
       for(int i = 1; i < n; i++) {
           for( int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                swap(a, j , j-1);
           }
       }
    }

    public static void swap(Object[]a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static <T extends Comparable<T>> boolean less(Comparable a1 , Comparable a2) {
        return  a1.compareTo(a2) < 0 ;
    }

    public static <T extends Comparable<T>> boolean less(T a1, T a2, Comparator comparator) {
       return  comparator.compare(a1, a2) < 0 ;
    }
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Insertion.sort(a);
        show(a);
    }
}
