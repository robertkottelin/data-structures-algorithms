package src;

import java.util.Comparator;
// import src.Node;

/*
A riority queue implemented using a singly linked list. 
The PriorityQueue class has two constructors: one that uses the natural ordering of the elements and another that accepts a custom comparator for priority ordering. 
The enqueue method inserts an item into the priority queue according to its priority, while the dequeue method removes and returns the highest priority item from the queue. 
The peek method returns the highest priority
*/

class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}

public class PriorityQueue<T extends Comparable<T>> {
    private int length;
    private Node<T> head;
    private Comparator<T> comparator;

    // Default constructor using natural ordering
    public PriorityQueue() {
        this.head = null;
        this.length = 0;
        this.comparator = null;
    }

    // Constructor with custom comparator for priority ordering
    public PriorityQueue(Comparator<T> comparator) {
        this.head = null;
        this.length = 0;
        this.comparator = comparator;
    }

    // Compare two items using the provided comparator or natural ordering
    private int compare(T item1, T item2) {
        if (comparator == null) {
            return ((Comparable<T>) item1).compareTo(item2);
        } else {
            return comparator.compare(item1, item2);
        }
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);

        // If the queue is empty or the new item has higher priority than the current head
        if (this.head == null || compare(item, this.head.value) > 0) {
            newNode.next = this.head;
            this.head = newNode;
        } else {
            Node<T> currentNode = this.head;

            // Traverse the list until we find the correct position for the new item
            while (currentNode.next != null && compare(item, currentNode.next.value) <= 0) {
                currentNode = currentNode.next;
            }

            // Insert the new node after the currentNode
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }

        this.length++;
    }

    public T dequeue() {
        if (this.head == null) {
            return null;
        }

        this.length--;

        Node<T> oldHead = this.head;
        this.head = this.head.next;

        oldHead.next = null;

        return oldHead.value;
    }

    public T peek() {
        return this.head != null ? this.head.value : null;
    }

    public int size() {
        return this.length;
    }


    public static void main(String[] args) {
        // Create a PriorityQueue using natural ordering
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        // Enqueue some elements
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(5);
        queue.enqueue(2);

        // Dequeue and print the elements in descending order
        while (queue.size() > 0) {
            System.out.println(queue.dequeue());
        }
    }

    public void add(ListNode list) {
    }

    public void poll() {
    }
}

// USAGE:
// Create a PriorityQueue with a custom Comparator to determine priority ordering. 
// If no comparator is provided, the natural ordering will be used
// Comparator<Integer> customComparator = (a, b) -> b - a;
// PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(customComparator);
