/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    static class Node<Item> {
        private Node<Item> prev;
        private Item item;
        private Node<Item> next;

        Node(Item item) {
            prev = null;
            this.item = item;
            next = null;
        }
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node<Item> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        Node<Item> newNode = new Node<>(item);
        if (isEmpty()) {
            tail = newNode;
            head = tail;
        } else {
            tail.next = newNode;
            newNode.next = null;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = head.item;
        head = head.next;
        head.prev = null;
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = tail.item;

        tail = tail.prev;
        tail.next = null;
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LLIter(head);
    }

    class LLIter implements Iterator {
        private Node<Item> current;

        LLIter(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque();
        d.addFirst(1);
        d.addFirst(3);
        d.addFirst(5);
        d.addLast(2);
        d.addLast(4);
        for (int i : d) {
            System.out.println(i);
        }
        d.removeFirst();
        d.removeLast();
        System.out.println();
        for (int i : d) {
            System.out.println(i);
        }

    }

}
