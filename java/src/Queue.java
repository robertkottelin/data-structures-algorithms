package src;

// import src.Node;

// Node class represents an element in the queue with its value and a reference to the next node.
class QueueNode<T> {
    T value; // The value of the node
    QueueNode<T> next; // A reference to the next node in the queue

    // Node constructor takes a value and initializes the next reference to null
    public QueueNode(T value) {
        this.value = value;
        this.next = null;
    }
}

// Queue class represents a queue data structure using generic type T
public class Queue<T> {
    public int length; // The number of elements in the queue
    private QueueNode<T> head; // The first element in the queue
    private QueueNode<T> tail; // The last element in the queue

    // Queue constructor initializes an empty queue with head and tail as null and length as 0
    public Queue() {
        this.head = this.tail = null;
        this.length = 0;
    }

    // enqueue method adds an item to the end of the queue
    public void enqueue(T item) {
        QueueNode<T> node = new QueueNode<>(item); // Create a new node with the given item
        this.length++; // Increment the length of the queue

        // If the queue is empty, set the head and tail to the new node
        if (this.tail == null) {
            this.tail = this.head = node;
            return;
        }

        // Otherwise, set the current tail's next reference to the new node and update the tail
        this.tail.next = node;
        this.tail = node;
    }

    // dequeue method removes and returns the item at the front of the queue
    public T dequeue() {
        // If the queue is empty, return null
        if (this.head == null) {
            return null;
        }

        // Decrement the length of the queue
        this.length--;

        // Store the current head node, update the head to the next node
        QueueNode<T> head = this.head;
        this.head = this.head.next;

        // Set the old head's next reference to null and return its value
        head.next = null;
        return head.value;
    }

    // peek method returns the value of the first element in the queue without removing it
    public T peek() {
        return this.head != null ? this.head.value : null;
    }

    public void add(int source) {
    }

    public boolean isEmpty() {
        return false;
    }

    public int poll() {
        return 0;
    }
}
