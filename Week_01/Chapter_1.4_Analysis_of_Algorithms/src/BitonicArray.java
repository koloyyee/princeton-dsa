public class BitonicArray {
    public static void main(String[] args) {


    }
    static int slow(int[] ints, int target) {
        for(int i = 0; i < ints.length; i++) {
            if(ints[i] == target) {
                return ints[i];
            }
        }
        return -1;
    }
    static int faster(int[] ints, int target) {
        int lo = ints[0];
        int hi = ints[ints.length -1];
        while (lo <= hi ) {
            int mid = lo + ( hi - lo) /2;
            if(target > ints[mid] ) {
                lo = mid + 1;
            } else if (target < ints[mid] ) {
                hi = mid - 1;
            } else {
                return ints[mid];
            }
        }

        return -1;
    }

    static int fasterR(int[] ints, int target, int lo, int hi) {
        while ( lo <= hi ) {

            int mid = lo + ( hi - lo ) / 2;
            if( ints[mid] == target ) {
                return ints[mid];
            } else if (target > ints[mid]){
                return fasterR(ints, target, mid + 1, hi);
            } else {
                return fasterR(ints, target, lo, mid - 1);
            }
        }
       return -1;
    }
}
