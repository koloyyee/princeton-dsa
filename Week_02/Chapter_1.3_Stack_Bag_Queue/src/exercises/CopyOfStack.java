package exercises;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* an excuse to learn stack by writing */
public class CopyOfStack <Item> implements Iterable<Item> {
    // like queue, we can implement it with linked list

    static class Node<Item>  {
        private Item item;
        private Node<Item> next;

        Node() {

        }

        Node (Node x) { // for copying with recursion
          item = (Item) x.item;
          if(x.next != null) {
              next = new Node(x.next);
          }
        }
    }

    private Node<Item> first; // top of the stack
    private int n; // size of the stack
    public CopyOfStack() {
        first = null;
        n = 0;
    }
    public CopyOfStack(CopyOfStack<Item> stack) {
       first = new Node(stack.first);
    }
public Node<Item> copy(Node<Item> node) { // for copying with recursion
       Item item = node.item;
       if(node.next != null) {
           return copy(node.next);
       } else {
           return null;
       }
}
  public int size() {
      return n;
  }
   public boolean isEmpty() {
       return n == 0;
   }
    public void push(Item item) {
        Node<Item> oldItem = first;
        first = new Node<Item>(); // creat a new node
        first.item = item; // place the new item at first
        first.next = oldItem; // PUSH the last first item to the back
       n++;
    }
    public Item peek(){
      if(isEmpty()) throw new NoSuchElementException("Stack underflow");
      return first.item;
    }
    public Item pop() {
      if(isEmpty()) throw new NoSuchElementException("Stack underflow");
      Item item = first.item;
      first = first.next; // override by the item below
       n--;
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
    public Iterator<Item> iterator() { return new LinkedIterator(first);}

    private class LinkedIterator implements Iterator<Item>{

      private Node<Item> current;

      public LinkedIterator(Node<Item> first) {
          current = first;
      }
        @Override
        public boolean hasNext() {
           return current != null;
        }
        @Override
        public Item next() {
          if(!hasNext()) throw new NoSuchElementException();
              Item item = current.item;
              current = current.next; // popping off by reversing
              return item;
        }
    }


    public static void main(String[] args) {
        CopyOfStack<String> stack = new CopyOfStack<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                stack.push(item);
            else if (!stack.isEmpty())
                StdOut.print(stack.pop() + " ");
        }
        StdOut.println("Original: " + stack);
        StdOut.println("(" + stack.size() + " left on stack)");

        CopyOfStack c = new CopyOfStack(stack);
        StdOut.println("CopyOfStack: " + c);
    }
}
