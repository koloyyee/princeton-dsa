/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAP = 8;
    private int head;
    private int tail;
    private int size;
    private Item[] q;

    // construct an empty randomized queue
    public RandomizedQueue() {
        head = 0;
        tail = 0;
        size = 0;
        q = (Item[]) new Object[INIT_CAP];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        if (size == q.length) {
            resize(size * 2);
        }
        if (tail == q.length) {
            tail = 0;
        }
        q[tail++] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randInt = StdRandom.uniformInt(size);
        Item item = q[randInt];
        q[randInt] = q[--tail];
        q[tail] = null;
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randInt = StdRandom.uniformInt(size);
        Item item = q[randInt];
        return item;
    }

    private void resize(int newCap) {
        if (newCap < size)
            throw new AssertionError();
        Item[] copy = (Item[]) new Object[newCap];
        for (int i = 0; i < size; i++) {
            copy[i] = q[(i + head) % q.length];
        }
        q = copy;
        head = 0;
        tail = size;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new LLIter<>();
    }

    private class LLIter<Item> implements Iterator<Item> {

        private int current;

        public LLIter() {
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = (Item) q[(current + head) % q.length];
            current++;
            return item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }

}