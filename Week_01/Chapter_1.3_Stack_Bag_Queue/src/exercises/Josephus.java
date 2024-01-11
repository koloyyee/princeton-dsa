package exercises;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * In the Josephus problem from antiquity,
 * N people are in dire straits
 * and agree to the following strategy to reduce the population.
 * They arrange themselves in a circle
 * (at positions numbered from 0 to N-1)
 * and proceed around the circle,
 * eliminating every Mth person until only one person is left.
 * Legend has it that Josephus figured out
 * where to sit to avoid being eliminated.
 *
 * Write a Queue client Josephus.java that
 * takes M and N from the command line
 * and prints out the order in which people are eliminated
 * (and thus would show Josephus where to sit in the circle).
 */

/* an excuses to learn queue by writing*/
public class Josephus<Item> implements Iterable<Item>{

    private  int n; // number of items in the queue.
    private Node<Item> first; // first to remove first
    private Node<Item> last; // add things here.


    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Josephus() {
        first = null;
        last = null;
        n = 0;
    }

    public int size() {
    return n;
    }
   public boolean isEmpty() {
        return n == 0;
   }
   public Item peek(){
        if( isEmpty()) throw new NoSuchElementException("Josephus underflow") ;
       return first.item;
   }

   public void enqueue(Item item) {
        Node<Item> oldLast = last; // save the last one
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last; // first in the queue
        } else {
            oldLast.next = last; // push to behind.
        }
       n++ ;
   }

   public Item dequeue(){
       if( isEmpty()) throw new NoSuchElementException("Josephus underflow") ;
       Item item = first.item; // to be returned
       first = first. next;
        n--;
        if(isEmpty()) {
            last = null;
        }
        return item;
   }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first){
            current = first;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item item = current.item; // return the first
            current = current.next; // set the pointer to next.
            return item;
        }
    }
    public static void main(String[] args) {
        if ( args.length != 2 ) {
            return;
        }
        int mth = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);
        Josephus<Integer> jo = new Josephus<Integer>();
        // populate the queue

        for (int i = 0; i < N; i++){
            jo.enqueue(i);
        }
        System.out.println("q: " + jo);
        while(!jo.isEmpty()) {
            for(int i = 0; i < mth -1; i++) {
                jo.enqueue(jo.dequeue()); // wrapped around. because people are in a circle!
                System.out.println("q: " + jo);
            }
            System.out.print(jo.dequeue()+ " "); // person got killed.
            System.out.println();
            System.out.println("q: " + jo);
        }
        System.out.println();
    }
}
