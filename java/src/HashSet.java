package src;

// Implement a basic hash set data structure to store unique integer elements.

import java.util.LinkedList;

public class HashSet {

    private static final int BUCKET_SIZE = 16;
    private LinkedList<Integer>[] buckets;

    public HashSet() {
        buckets = new LinkedList[BUCKET_SIZE];
        for (int i = 0; i < BUCKET_SIZE; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public int hash(int value) {
        return value % BUCKET_SIZE;
    }

    public void add(int value) {
        int index = hash(value);
        if (!buckets[index].contains(value)) {
            buckets[index].add(value);
        }
    }

    public boolean contains(int value) {
        int index = hash(value);
        return buckets[index].contains(value);
    }

    public void remove(int value) {
        int index = hash(value);
        buckets[index].remove(Integer.valueOf(value));
    }
}
