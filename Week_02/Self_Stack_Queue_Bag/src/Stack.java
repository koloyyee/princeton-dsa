
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Stack<Item> {
    private Node<Item> top;

    public static void main(String[] args) {

    }

    // Node for linked list
    static class Node<Item> {
        Item item;
        Node<Item> next;
    }
    public Stack() {
        top = null;
    }
    public void push(Item item){
        Node<Item> node = new Node<>();
        if( top == null){
            top = node;
        }
        top.item = item;
        top.next = node;

    }
    public void pop(){}
    public boolean isEmpty(){}
}