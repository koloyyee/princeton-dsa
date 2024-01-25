/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    static final int INIT_CAP = 8;
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
        if (size == q.length) {
            resize(size * 2);
        }
        q[tail++] = item;
        if (tail == q.length) {
            tail = 0;
        }
        size++;
        shuffleItem();
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = q[--size];
        if (size > 0 && size == q.length / 4) {
            resize(q.length / 2);
        }
        q[size] = null;
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

    public boolean validate(int index) {
        return index < 0 || index > size;
    }

    public void resize(int newCap) {
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

    public void shuffleItem() {
        int randInt = StdRandom.uniformInt(size);
        Item temp = q[randInt];
        q[randInt] = q[size - 1];
        q[size - 1] = temp;

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new LLIter();
    }

    private class LLIter implements Iterator {

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
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = q[(current + head) % q.length];
            current++;
            return item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> r = new RandomizedQueue();
        r.enqueue(1);
        r.enqueue(2);
        r.enqueue(3);
        r.enqueue(4);

        for (int i : r) {
            System.out.println(i);
        }
        System.out.println("-------------");
        r.dequeue();
        for (int i : r) {
            System.out.println(i);
        }
    }

}