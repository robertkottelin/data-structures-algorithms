package src;

import java.lang.reflect.Array;

public class CustomArrayList<T> {
    private int length;
    private T[] items;

    public CustomArrayList(Class<T> clazz) {
        this.length = 0;
        this.items = (T[]) Array.newInstance(clazz, 0);
    }

    void prepend(T item) {
        T[] newItems = (T[]) Array.newInstance(items.getClass().getComponentType(), length + 1);
        System.arraycopy(items, 0, newItems, 1, length);
        newItems[0] = item;
        items = newItems;
        length++;
    }

    void insertAt(T item, int idx) {
        if (idx < 0 || idx > this.length) {
            throw new RuntimeException("Index out of bounds");
        }

        T[] newItems = (T[]) Array.newInstance(items.getClass().getComponentType(), length + 1);
        System.arraycopy(items, 0, newItems, 0, idx);
        System.arraycopy(items, idx, newItems, idx + 1, length - idx);
        newItems[idx] = item;
        items = newItems;
        length++;
    }

    void append(T item) {
        T[] newItems = (T[]) Array.newInstance(items.getClass().getComponentType(), length + 1);
        System.arraycopy(items, 0, newItems, 0, length);
        newItems[length] = item;
        items = newItems;
        length++;
    }

    T remove(T item) {
        int idx = indexOf(item);
        if (idx == -1) {
            return null;
        }

        T[] newItems = (T[]) Array.newInstance(items.getClass().getComponentType(), length - 1);
        System.arraycopy(items, 0, newItems, 0, idx);
        System.arraycopy(items, idx + 1, newItems, idx, length - idx - 1);
        items = newItems;
        length--;
        return item;
    }

    T get(int idx) {
        if (idx < 0 || idx >= this.length) {
            return null;
        }

        return this.items[idx];
    }

    T removeAt(int idx) {
        if (idx < 0 || idx >= this.length) {
            return null;
        }

        T removedItem = items[idx];
        T[] newItems = (T[]) Array.newInstance(items.getClass().getComponentType(), length - 1);
        System.arraycopy(items, 0, newItems, 0, idx);
        System.arraycopy(items, idx + 1, newItems, idx, length - idx - 1);
        items = newItems;
        length--;
        return removedItem;
    }

    private int indexOf(T item) {
        for (int i = 0; i < length; i++) {
            if (items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
}
