package src;

class CustomHashMap {
    class Entry {
        int key;
        int value;
        Entry next;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 16;
    private Entry[] table;

    public CustomHashMap() {
        table = new Entry[SIZE];
    }

    public void put(int key, int value) {
        int hash = key % SIZE;
        Entry existing = table[hash];

        if (existing == null) {
            table[hash] = new Entry(key, value);
        } else {
            while (existing.next != null) {
                if (existing.key == key) {
                    existing.value = value;
                    return;
                }
                existing = existing.next;
            }

            if (existing.key == key) {
                existing.value = value;
            } else {
                existing.next = new Entry(key, value);
            }
        }
    }

    public int get(int key) {
        int hash = key % SIZE;
        Entry existing = table[hash];

        if (existing == null) {
            return -1;
        } else {
            while (existing != null) {
                if (existing.key == key) {
                    return existing.value;
                }
                existing = existing.next;
            }
        }
        return -1;
    }
}

