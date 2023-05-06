use std::collections::HashMap;
use std::collections::VecDeque;

struct LRUCache {
    capacity: usize,
    cache: HashMap<i32, i32>,
    usage: VecDeque<i32>,
}

impl LRUCache {
    fn new(capacity: usize) -> Self {
        LRUCache {
            capacity,
            cache: HashMap::new(),
            usage: VecDeque::with_capacity(capacity),
        }
    }

    fn get(&mut self, key: i32) -> i32 {
        if let Some(&value) = self.cache.get(&key) {
            self.usage.remove_item(&key);
            self.usage.push_front(key);
            value
        } else {
            -1
        }
    }

    fn put(&mut self, key: i32, value: i32) {
        if self.cache.contains_key(&key) {
            self.usage.remove_item(&key);
        } else if self.usage.len() == self.capacity {
            let removed_key = self.usage.pop_back().unwrap();
            self.cache.remove(&removed_key);
        }
        self.cache.insert(key, value);
        self.usage.push_front(key);
    }
}
