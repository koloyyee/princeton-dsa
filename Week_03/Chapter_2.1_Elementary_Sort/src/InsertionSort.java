/**
 * Insert Sort is an in place sort
 *
 * */
public class InsertionSort{

    public static void main(String[] args) {
        int[] a = {2 , 5, 4, 6, 8, 1, 9};
        for(int i : a) {
            System.out.print( i + " ");
        }
        System.out.println();
         sort(a);
        for(int i : a ) {
            System.out.print( i + " ");
        }
    }

    private static void sort(int[] arr) {
       // we need walk through the array
        /* *
        * we are going to start <i>i</i> with index 1
        * because we j will be i, to check the one before we'll need j - 1
        * so i need to offset by 1;
        * */
       for(int i = 1; i < arr.length; i++ )  {
           /** Crux of the algorithm */
           /** j > 0 to avoid out of low bound e.g.: if i = 1, j-- then loop will not run.**/
           /** this will keep looping if left is smaller because j-- */
           for( int j = i; j > 0 ; j--) {
               /** a[j] is current, a[j-1] is 1 before */
               if(arr[j] < arr[j-1]) {
                   /** swap if the left is smaller than right */
                   int temp = arr[j-1];
                   arr[j-1] = arr[j];
                   arr[j] = temp;
               }
           }
       }
    }
}