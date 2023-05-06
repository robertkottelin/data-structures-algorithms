package src;
import java.util.HashMap;
import java.util.Map;

public class CustomMap<T, V> {
    private Map<T, V> items;

    public CustomMap() {
        this.items = new HashMap<>();
    }

    public V get(T key) {
        return items.get(key);
    }

    public void set(T key, V value) {
        items.put(key, value);
    }

    public V delete(T key) {
        V deletedValue = items.get(key);
        if (deletedValue != null) {
            items.remove(key);
            return deletedValue;
        }
        return null;
    }

    public int size() {
        return items.size();
    }
}
