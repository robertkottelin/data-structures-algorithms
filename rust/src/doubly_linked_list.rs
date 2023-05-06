#[derive(Clone)]
pub struct Node<T> {
    value: T,
    next: Option<Box<Node<T>>>,
    prev: Option<Box<Node<T>>>,
}

pub struct DoublyLinkedList<T> {
    length: usize,
    head: Option<Box<Node<T>>>,
    tail: Option<Box<Node<T>>>,
}

impl<T: Clone> DoublyLinkedList<T> {
    pub fn new() -> Self {
        DoublyLinkedList {
            length: 0,
            head: None,
            tail: None,
        }
    }

    pub fn prepend(&mut self, item: T) {
        let mut node = Box::new(Node {
            value: item,
            next: self.head.take(),
            prev: None,
        });

        self.length += 1;

        if let Some(ref mut head) = node.next {
            head.prev = Some(node.clone());
        } else {
            self.tail = Some(node.clone());
        }

        self.head = Some(node);
    }

    pub fn insert_at(&mut self, item: T, idx: usize) {
        if idx > self.length {
            panic!("Index out of bounds");
        } else if idx == self.length {
            self.append(item);
            return;
        } else if idx == 0 {
            self.prepend(item);
            return;
        }

        self.length += 1;
        let mut curr = self.head.as_mut();

        for _ in 0..idx {
            curr = curr.unwrap().next.as_mut();
        }

        let mut node = Box::new(Node {
            value: item,
            prev: curr.as_mut().unwrap().prev.take(),
            next: curr.take(),
        });

        if let Some(ref mut prev) = node.prev {
            prev.next = Some(node.clone());
        }

        if let Some(ref mut next) = node.next {
            next.prev = Some(node.clone());
        }

        curr.replace(node);
    }

    pub fn append(&mut self, item: T) {
        let mut node = Box::new(Node {
            value: item,
            next: None,
            prev: self.tail.take(),
        });

        self.length += 1;

        if let Some(ref mut tail) = node.prev {
            tail.next = Some(node.clone());
        } else {
            self.head = Some(node.clone());
        }

        self.tail = Some(node);
    }

    pub fn remove(&mut self, item: T) -> Option<T>
    where
        T: PartialEq,
    {
        let mut curr = self.head.as_mut();

        while let Some(ref mut node) = curr {
            if node.value == item {
                break;
            }
            curr = node.next.as_mut();
        }

        curr.map(|node| self.remove_node(node))
    }

    pub fn get(&self, idx: usize) -> Option<&T> {
        let mut curr = self.head.as_ref();

        for _ in 0..idx {
            curr = curr?.next.as_ref();
        }

        curr.map(|node| &node.value)
    }

    pub fn remove_at(&mut self, idx: usize) -> Option<T> {
        let node = self.get_node_at(idx)?.take();
        self.remove_node(node)
    }

    fn remove_node(&mut self, mut node: Box<Node<T>>) -> Option<T> {
        self.length -= 1;

        if let Some(ref mut prev) = node.prev.as_mut() {
            prev.next = node.next.take();
        }

        if let Some(ref mut next) = node.next.as_mut() {
            next.prev = node.prev.take();
        }

        if node.prev.is_none() {
            self.head = node.next.take();
        }
        if node.next.is_none() {
            self.tail = node.prev.take();
        }

        node.prev = None;
        node.next = None;

        Some(node.value)
    }

    fn get_node_at(&mut self, idx: usize) -> Option<&mut Box<Node<T>>> {
        let mut curr = self.head.as_mut();

        for _ in 0..idx {
            curr = curr?.next.as_mut();
        }

        curr
    }
}