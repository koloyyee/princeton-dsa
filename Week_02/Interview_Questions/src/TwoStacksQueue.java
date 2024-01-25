import edu.princeton.cs.algs4.Stack;

/**
 * Interview Question 1.
 *
 * Queue with two stacks.
 * Implement a queue with two stacks
 * so that each queue operations takes a constant amortized number of stack operations.
 * */

public class TwoStacksQueue<Item> {
    private Stack<Item> inbox;
    private Stack<Item> outbox;

    public void enqueue(Item item) {
        inbox.push(item);
    }

    public Item dequeue() {
        if(outbox.isEmpty()) {
            while(!inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }
        }
       return outbox.pop();
    }
}
