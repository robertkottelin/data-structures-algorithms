package src;
import java.util.HashMap;
import java.util.Map;

class Node<T> {
    T value;
    Node<T> next;
    Node<T> prev;
}

public class LRU<K, V> {
    private int length;
    private Node<V> head;
    private Node<V> tail;

    private Map<K, Node<V>> lookup;
    private Map<Node<V>, K> reverseLookup;

    private final int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.length = 0;
        this.head = null;
        this.tail = null;
        this.lookup = new HashMap<>();
        this.reverseLookup = new HashMap<>();
    }

    public void update(K key, V value) {
        Node<V> node = lookup.get(key);
        if (node == null) {
            node = new Node<>();
            node.value = value;
            length++;
            prepend(node);
            trimCache();
            lookup.put(key, node);
            reverseLookup.put(node, key);
        } else {
            detach(node);
            prepend(node);
            node.value = value;
        }
    }

    public V get(K key) {
        Node<V> node = lookup.get(key);
        if (node == null) {
            return null;
        }
        detach(node);
        prepend(node);
        return node.value;
    }

    private void detach(Node<V> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (length == 1) {
            head = tail = null;
        }
        if (head == node) {
            head = head.next;
        }
        if (tail == node) {
            tail = tail.prev;
        }

        node.next = null;
        node.prev = null;
    }

    private void prepend(Node<V> node) {
        if (head == null) {
            head = tail = node;
            return;
        }

        node.next = head;
        head.prev = node;
        head = node;
    }

    private void trimCache() {
        if (length <= capacity) {
            return;
        }
        Node<V> tailNode = tail;
        detach(tail);

        K key = reverseLookup.get(tailNode);
        lookup.remove(key);
        reverseLookup.remove(tailNode);
        length--;
    }
}
