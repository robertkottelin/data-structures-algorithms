package src;

// last in, first out

// Generic class for a Node in the Stack with a value of type T
class StackNode<T> {
    T value; // value of the node
    StackNode<T> prev; // reference to the previous node in the stack

    // Constructor for the Node class
    public StackNode(T value) {
        this.value = value;
        this.prev = null;
    }
}

// Generic Stack class using type T
public class Stack<T> {
    public int length; // length of the stack
    private StackNode<T> head; // reference to the top node in the stack

    // Constructor for the Stack class
    public void StackNode() {
        this.head = null;
        this.length = 0;
    }

    // Method to push an item onto the stack
    public void push(T item) {
        StackNode<T> node = new StackNode<>(item);

        this.length++;
        if (this.head == null) {
            this.head = node;
            return;
        }
        node.prev = this.head; // set the previous node to the current head
        this.head = node; // set the head to the new node
    }

    // Method to pop an item off the stack
    public T pop() {
        this.length = Math.max(0, this.length - 1);
        if (this.length == 0) {
            StackNode<T> head = this.head;
            this.head = null;
            return head != null ? head.value : null;
        }

        StackNode<T> head = this.head;
        this.head = head.prev;

        return head.value;
    }

    // Method to peek at the top item in the stack without removing it
    public T peek() {
        return this.head != null ? this.head.value : null;
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return false;
    }
}
