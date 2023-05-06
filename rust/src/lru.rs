use std::rc::Rc;
use std::cell::RefCell;
use std::collections::HashMap;

#[derive(Clone)]
pub struct Node<T> {
    pub value: T,
    pub next: Option<Rc<RefCell<Node<T>>>>,
    pub prev: Option<Rc<RefCell<Node<T>>>>,
}

fn create_node<V>(value: V) -> Node<V> {
    Node {
        value,
        next: None,
        prev: None,
    }
}

pub struct LRU<K, V>
where
    K: std::cmp::Eq + std::hash::Hash + Clone,
    V: Clone,
{
    capacity: usize,
    length: usize,
    head: Option<Rc<RefCell<Node<V>>>>,
    tail: Option<Rc<RefCell<Node<V>>>>,
    lookup: HashMap<K, Rc<RefCell<Node<V>>>>,
    reverse_lookup: HashMap<Rc<RefCell<Node<V>>>, K>,
}

impl<K, V> LRU<K, V>
where
    K: std::cmp::Eq + std::hash::Hash + Clone,
    V: Clone,
{
    pub fn new(capacity: usize) -> LRU<K, V> {
        LRU {
            capacity,
            length: 0,
            head: None,
            tail: None,
            lookup: std::collections::HashMap::new(),
            reverse_lookup: std::collections::HashMap::new(),
        }
    }

    pub fn update(&mut self, key: K, value: V) {
        let mut node = self.lookup.get(&key).cloned();

        if node.is_none() {
            let new_node = Box::new(create_node(value));
            self.length += 1;
            self.prepend(new_node.clone());
            self.trim_cache();
            self.lookup.insert(key.clone(), new_node.clone());
            self.reverse_lookup.insert(new_node, key);
        } else {
            let mut node = node.unwrap();
            self.detach(&mut node);
            self.prepend(node.clone());
            node.value = value;
        }
    }

    pub fn get(&mut self, key: K) -> Option<&V> {
        let node = self.lookup.get(&key);

        match node {
            None => None,
            Some(node) => {
                let mut node = node.clone();
                self.detach(&mut node);
                self.prepend(node.clone());
                Some(&node.value)
            }
        }
    }

    fn detach(&mut self, node: &mut Box<Node<V>>) {
        if let Some(prev) = node.prev.as_mut() {
            prev.next = node.next.clone();
        }
        if let Some(next) = node.next.as_mut() {
            next.prev = node.prev.clone();
        }

        if self.length == 1 {
            self.head = None;
            self.tail = None;
        }

        if self.head.as_ref().map_or(false, |head| head.as_ref() as *const _ == node.as_ref() as *const _) {
            self.head = node.next.clone();
        }

        if self.tail.as_ref().map_or(false, |tail| tail.as_ref() as *const _ == node.as_ref() as *const _) {
            self.tail = node.prev.clone();
        }

        node.next = None;
        node.prev = None;
    }

    fn prepend(&mut self, node: Box<Node<V>>) {
        match self.head {
            None => {
                self.head = Some(node.clone());
                self.tail = Some(node);
            }
            Some(_) => {
                node.next = self.head.clone();
                self.head.as_mut().unwrap().prev = Some(node.clone());
                self.head = Some(node);
            }
        }
    }

    fn trim_cache(&mut self) {
        if self.length <= self.capacity {
            return;
        }

        let tail = self.tail.take().unwrap();
        self.detach(&mut tail.clone());

        if let Some(key) = self.reverse_lookup.get(&tail) {
            let key = key.clone();
            self.lookup.remove(&key);
            self.reverse_lookup.remove(&tail);
        }
        self.length -= 1;
    }
}
