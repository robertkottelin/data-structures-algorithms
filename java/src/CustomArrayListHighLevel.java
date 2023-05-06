package src;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayListHighLevel<T> {
    private List<T> items;

    public CustomArrayListHighLevel() {
        this.items = new ArrayList<>();
    }

    void prepend(T item) {
        items.add(0, item);
    }

    void insertAt(T item, int idx) {
        items.add(idx, item);
    }

    void append(T item) {
        items.add(item);
    }

    T remove(T item) {
        boolean removed = items.remove(item);
        return removed ? item : null;
    }

    T get(int idx) {
        if (idx < 0 || idx >= items.size()) {
            return null;
        }

        return items.get(idx);
    }

    T removeAt(int idx) {
        if (idx < 0 || idx >= items.size()) {
            return null;
        }

        return items.remove(idx);
    }

    private int indexOf(T item) {
        return items.indexOf(item);
    }
}
