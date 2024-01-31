package own;

public class Insertion {
    private Insertion(){}

    public static <T extends Comparable<T>> void sort(Comparable[] a) {
       int n = a.length;
       for(int i = 1; i < n; i++) {
           for( int j = i; j > 0 && less(a[j], a[i])) {

           }
       }
    }

    public static <T extends Comparable<T>> boolean less(Comparable a1, a2) {
       return a1.compareTo(a2)
    }
    public static void main(String[] args) {

    }
}
