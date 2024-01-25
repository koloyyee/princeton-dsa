import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    static class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;

        Node(Item newItem) {
            item = newItem;
            prev = null;
            next = null;
        }

        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", prev=" + prev +
                    ", next=" + next +
                    '}';
        }
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int n;

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    /**
     * Corner cases.  Throw the specified exception for the following corner cases:
     */

    /**
     * Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a
     * null argument.
     */
    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node newHead = new Node<>(item);
        if (isEmpty()) {
            head = newHead;
            tail = head;
        }
        else {
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node newTail = new Node<>(item);
        if (isEmpty()) {
            tail = newTail;
            head = tail;
        }
        else {
            tail.next = newTail;
            newTail.prev = tail;
            tail = newTail;
        }
        n++;
    }


    /**
     * Throw a java.util.NoSuchElementException if the client calls either removeFirst() or
     * removeLast when the deque is empty.
     **/
    // remove and return the item from the front
    public Item removeFirst() {
        Item item = head.item;
        if (isEmpty()) {
            head = null;
            tail = null;
        }
        else {
            head.next.prev = null;
            head = head.next;
        }
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item item = tail.item;
        if (isEmpty()) {
            tail = null;
            head = null;
        }
        else {
            tail.prev.next = null;
            tail = tail.prev;
        }
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iter(head);
    }

    /**
     * Throw a java.util.NoSuchElementException if the client calls the next() method in the
     * iterator when there are no more items to return.
     * Throw an UnsupportedOperationException if the client calls the remove() method in the
     * iterator.
     */
    private class Iter implements Iterator<Item> {
        private Node<Item> current;

        Iter(Node<Item> head) {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque d = new Deque();
        d.addFirst(1);
        d.addFirst(3);
        d.addLast(2);
        System.out.println(d.toString());
        d.removeFirst();
        System.out.println(d.toString());
        d.removeLast();
        System.out.println(d.toString());

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }
}
