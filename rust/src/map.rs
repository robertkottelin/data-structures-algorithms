use std::collections::HashMap;
use std::hash::Hash;

pub struct CustomMap<T: Eq + Hash + Clone, V> {
    items: HashMap<T, V>,
}

impl<T: Eq + Hash + Clone, V> CustomMap<T, V> {
    pub fn new() -> CustomMap<T, V> {
        CustomMap {
            items: HashMap::new(),
        }
    }

    pub fn get(&self, key: T) -> Option<&V> {
        self.items.get(&key)
    }

    pub fn set(&mut self, key: T, value: V) {
        self.items.insert(key, value);
    }

    pub fn delete(&mut self, key: T) -> Option<V> {
        self.items.remove(&key)
    }

    pub fn size(&self) -> usize {
        self.items.len()
    }
}


#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_map() {
        let mut map = CustomMap::new();
        map.set("a", 1);
        map.set("b", 2);
        map.set("c", 3);

        assert_eq!(map.get("a"), Some(&1));
        assert_eq!(map.get("b"), Some(&2));
        assert_eq!(map.get("c"), Some(&3));
        assert_eq!(map.get("d"), None);

        assert_eq!(map.delete("a"), Some(1));
        assert_eq!(map.delete("a"), None);
        assert_eq!(map.get("a"), None);
        assert_eq!(map.get("b"), Some(&2));
        assert_eq!(map.get("c"), Some(&3));
        assert_eq!(map.get("d"), None);

        assert_eq!(map.size(), 2);
    }
}