package src;

public class DoublyLinkedList<T> {
    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList() {
        this.length = 0;
        this.head = null;
        this.tail = null;
    }

    public void prepend(T item) {
        Node<T> node = new Node<>(item);
        this.length++;

        if (head == null) {
            head = tail = node;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;
    }

    public void insertAt(T item, int idx) {
        if (idx > this.length) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else if (idx == this.length) {
            this.append(item);
            return;
        } else if (idx == 0) {
            this.prepend(item);
            return;
        }

        this.length++;
        Node<T> curr = head;
        for (int i = 0; i < idx; ++i) {
            curr = curr.next;
        }

        Node<T> node = new Node<>(item);
        node.next = curr;
        node.prev = curr.prev;
        curr.prev = node;

        if (curr.prev != null) {
            curr.prev.next = node;
        }
    }

    public void append(T item) {
        this.length++;
        Node<T> node = new Node<>(item);

        if (tail == null) {
            head = tail = node;
            return;
        }

        node.prev = tail;
        tail.next = node;
        tail = node;
    }

    public T remove(T item) {
        Node<T> curr = head;
        while (curr != null && !curr.value.equals(item)) {
            curr = curr.next;
        }

        if (curr == null) {
            return null;
        }

        this.length--;

        if (this.length == 0) {
            T out = head.value;
            head = tail = null;
            return out;
        }

        if (curr.prev != null) {
            curr.prev.next = curr.next;
        }

        if (curr.next != null) {
            curr.next.prev = curr.prev;
        }

        if (curr == head) {
            head = curr.next;
        }

        if (curr == tail) {
            tail = curr.prev;
        }

        curr.prev = curr.next = null;

        return curr.value;
    }

    public T get(int idx) {
        Node<T> curr = head;
        for (int i = 0; i < idx && curr != null; ++i) {
            curr = curr.next;
        }

        return curr != null ? curr.value : null;
    }

    public T removeAt(int idx) {
        Node<T> node = getAt(idx);

        if (node == null) {
            return null;
        }

        return removeNode(node);
    }

    private T removeNode(Node<T> node) {
        if (node == null) {
            return null;
        }

        this.length--;

        if (node.prev != null) {
            node.prev.next = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }

        if (node == head) {
            head = node.next;
        }

        if (node == tail) {
            tail = node.prev;
        }

        node.prev = node.next = null;

        return node.value;
    }

    private Node<T> getAt(int idx) {
        Node<T> curr = head;
        for (int i = 0; i < idx && curr != null; ++i) {
            curr = curr.next;
        }
        return curr;
    }

    public int size() {
        return this.length;
    }
}

/*
/////////////////////////////////////////////////////
// Implement insertion, deletion, and traversal
class DoublyLinkedList {
    class Node {
        int data;
        Node prev, next;
    }

    private Node head, tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public void insertAtEnd(int data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.prev = newNode.next = null;

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void delete(int data) {
        Node current = head;

        while (current != null) {
            if (current.data == data) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
 */