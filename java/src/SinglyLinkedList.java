package src;
// import src.ListNode;

class ListNode<T> {
    T value;
    ListNode<T> next;

    public ListNode(T value) {
        this.value = value;
        this.next = null;
    }
}

public class SinglyLinkedList<T> {
    public int length;
    private ListNode<T> head;

    public SinglyLinkedList() {
        this.length = 0;
    }

    public void prepend(T item) {
        ListNode<T> node = new ListNode<>(item);
        node.next = this.head;
        this.head = node;
        this.length++;
    }

    public void insertAt(T item, int idx) {
        if (idx < 0 || idx > this.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        if (idx == 0) {
            this.prepend(item);
            return;
        }

        ListNode<T> currentNode = this.head;
        ListNode<T> prevNode = null;
        int currentIndex = 0;

        while (currentIndex < idx) {
            prevNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }

        ListNode<T> newNode = new ListNode<>(item);
        newNode.next = currentNode;
        prevNode.next = newNode;
        this.length++;
    }

    public void append(T item) {
        if (this.head == null) {
            this.prepend(item);
            return;
        }

        ListNode<T> currentNode = this.head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = new ListNode<>(item);
        this.length++;
    }

    public T remove(T item) {
        if (this.head == null) {
            return null;
        }

        ListNode<T> currentNode = this.head;
        ListNode<T> prevNode = null;

        if (currentNode.value.equals(item)) {
            this.head = currentNode.next;
            this.length--;
            return item;
        }

        while (currentNode.next != null && !currentNode.value.equals(item)) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        if (currentNode.value.equals(item)) {
            prevNode.next = currentNode.next;
            this.length--;
            return item;
        }

        return null;
    }

    public T get(int idx) {
        if (idx < 0 || idx >= this.length) {
            return null;
        }

        ListNode<T> currentNode = this.head;
        int currentIndex = 0;

        while (currentNode != null && currentIndex < idx) {
            currentNode = currentNode.next;
            currentIndex++;
        }

        return currentNode != null ? currentNode.value : null;
    }

    public T removeAt(int idx) {
        if (idx < 0 || idx >= this.length) {
            return null;
        }

        if (idx == 0) {
            T value = this.head.value;
            this.head = this.head.next;
            this.length--;
            return value;
        }

        ListNode<T> currentNode = this.head;
        ListNode<T> prevNode = null;
        int currentIndex = 0;

        while (currentNode != null && currentIndex < idx) {
            prevNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }

        if (currentNode != null) {
            prevNode.next = currentNode.next;
            this.length--;
            return currentNode.value;
        }

        return null;
    }
}
