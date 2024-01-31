package own;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Selection {

    public static <T extends Comparable<T>> void sort(T[] a ) {
        int size = a.length;
        for(int i = 0; i < size; i++ ) {
            int minIndex = i;
            for(int j = i + 1; j < size; j++) {
                if(less(a[j], a[minIndex])) {
                    minIndex = j;
                }
            }
            swap(a, i, minIndex);
            show(a);
            assert isSorted(a, 0, i);
        }
    }

    public static <T extends Comparable<T>> boolean less(Comparable a1, Comparable a2) {
       return a1.compareTo(a2) < 0;
    }
    public static <T extends Comparable<T>> boolean less(Comparator<T> comparator, T a1, T a2) {
        return comparator.compare(a1, a2) < 0;
    }


    public static <T extends Comparable<T>> void swap(T[] a, int thisIndex, int thatIndex){
        T temp = a[thisIndex];
        a[thisIndex] = a[thatIndex];
        a[thatIndex] = temp;
    }

    public static <T extends Comparable<T>> boolean isSorted(Comparable[] a, int lo, int hi) {
        for(int i = lo + 1; i <= hi; i++){
            if(less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }
    public static <T extends Comparable<T>> boolean isSorted(T[] a, Comparator comparator,  int lo, int hi) {
        for(int i = lo; i < hi; i++) {
            if(less(comparator, a[i], a[i-1] )) {
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable<T>>void show(T[] a) {
       for(T item: a)  {
           System.out.print(item);
       }
        System.out.println();
    }
    public static void main(String[] args) {
        Selection.sort(args);
        show(args);
    }
}
