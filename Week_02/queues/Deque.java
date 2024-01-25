/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private static class Node<Item> {
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
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        Node<Item> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
            tail = head;
        }// I had my bug here
        else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        Node<Item> newNode = new Node<>(item);
        if (isEmpty()) {
            tail = newNode;
            head = tail;
        } // I had my bug here
        else {
            tail.next = newNode;
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
        if (head != null) {

            head.prev = null;
        }// I had my bug here
        else {
            tail = null;
        }
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
        if (tail != null) {
            tail.next = null;
        }// I had my bug here
        else {
            head = null;
        }
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LLIter<>(head);
    }

    private class LLIter<Item> implements Iterator<Item> {
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
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        for (int i : d) {
            System.out.println(i);
        }
        d.removeFirst();

    }

}
