use std::collections::VecDeque;

#[derive(Debug)]
pub struct ArrayList<T> {
    length: usize,
    items: VecDeque<T>,
}

impl<T: PartialEq> ArrayList<T> {
    pub fn new() -> Self {
        Self {
            length: 0,
            items: VecDeque::new(),
        }
    }

    pub fn prepend(&mut self, item: T) {
        self.items.push_front(item);
        self.length += 1;
    }

    pub fn insert_at(&mut self, item: T, idx: usize) {
        if idx < 0 || idx > self.length {
            panic!("Index out of bounds");
        }

        self.items.insert(idx, item);
        self.length += 1;
    }

    pub fn append(&mut self, item: T) {
        self.items.push_back(item);
        self.length += 1;
    }

    pub fn remove(&mut self, item: T) -> Option<T> {
        let idx = self.items.iter().position(|x| *x == item);
        if let None = idx {
            return None;
        }

        let removed_item = self.items.remove(idx.unwrap());
        self.length -= 1;
        removed_item
    }

    pub fn get(&self, idx: usize) -> Option<&T> {
        if idx < 0 || idx >= self.length {
            return None;
        }

        self.items.get(idx)
    }

    pub fn remove_at(&mut self, idx: usize) -> Option<T> {
        if idx < 0 || idx >= self.length {
            return None;
        }

        let item = self.items.remove(idx);
        self.length -= 1;
        item
    }
}
