/**
 * Interview Question 2.
 *
 * Stack with max.
 * Create a data structure
 * that efficiently supports the stack operations
 * (push and pop) and also a return-the-maximum operation.
 * Assume the elements are real numbers so that you can compare them.
 * */
public class StackWithMax {


    static class Node {
       private int item;
       private int cachedMax;
       private Node next;
        Node(){
        item = 0;
        cachedMax = 0;
        next = null;
    }
    }
    private Node top;
    private int max;

    public StackWithMax() {
        this.top = null;
        this.max = 0;
    }

    public boolean isEmpty(){return top == null;};

    public void push(int item) {
        if(item > max) {
            max = item;
        }
        Node oldTop = top;
        top = new Node();
        top.item = item;
        top.cachedMax = max;
        top.next = oldTop;

    }
    public int pop(){
        if(isEmpty()) throw new IllegalArgumentException("under flow");
        int item = top.item;
        top = top.next;
        return item;
    }

    public int max() {
        return top.cachedMax;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,0,4, 1,5,0, 4,2};
        StackWithMax swm = new StackWithMax();
        for(int el : nums) {
            System.out.println("El: " + el);
           swm.push(el);
        }
        System.out.println("max: " + swm.max());
        System.out.println("pop: " + swm.pop());
        System.out.println("max: " + swm.max());
        System.out.println("expected to be 5");

    }
}
