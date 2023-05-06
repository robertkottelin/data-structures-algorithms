pub struct ListNode<T> {
    value: T,
    next: Option<Box<ListNode<T>>>,
}

pub struct SinglyLinkedList<T> {
    length: usize,
    head: Option<Box<ListNode<T>>>,
}

impl<T> SinglyLinkedList<T> {
    pub fn new() -> SinglyLinkedList<T> {
        SinglyLinkedList {
            length: 0,
            head: None,
        }
    }

    pub fn prepend(&mut self, item: T) {
        let node = ListNode {
            value: item,
            next: self.head.take(),
        };
        self.head = Some(Box::new(node));
        self.length += 1;
    }

    pub fn insert_at(&mut self, item: T, idx: usize) -> Result<(), &'static str> {
        if idx > self.length {
            return Err("Index out of bounds");
        }

        if idx == 0 {
            self.prepend(item);
            return Ok(());
        }

        let mut current_node = self.head.as_mut();
        let mut prev_node: Option<&mut Box<ListNode<T>>> = None;
        let mut current_index = 0;

        while current_index < idx {
            prev_node = current_node;
            current_node = current_node.unwrap().next.as_mut();
            current_index += 1;
        }

        let new_node = ListNode {
            value: item,
            next: current_node.take().map(|node| Box::new(*node)),
        };
        prev_node.unwrap().next = Some(Box::new(new_node));
        self.length += 1;

        Ok(())
    }

    pub fn append(&mut self, item: T) {
        if self.head.is_none() {
            self.prepend(item);
            return;
        }

        let mut current_node = self.head.as_mut();

        while let Some(node) = current_node {
            if node.next.is_none() {
                node.next = Some(Box::new(ListNode { value: item, next: None }));
                self.length += 1;
                break;
            } else {
                current_node = node.next.as_mut();
            }
        }
    }

    pub fn remove(&mut self, item: T) -> Option<T>
    where
        T: PartialEq,
    {
        let mut current_node = self.head.as_mut();
        let mut prev_node: Option<&mut Box<ListNode<T>>> = None;

        if current_node.as_ref().map(|node| &node.value) == Some(&item) {
            self.head = self.head.unwrap().next;
            self.length -= 1;
            return Some(item);
        }

        while let Some(node) = current_node {
            if &node.value == &item {
                prev_node.unwrap().next = node.next.take();
                self.length -= 1;
                return Some(item);
            } else {
                prev_node = Some(node);
                current_node = node.next.as_mut();
            }
        }

        None
    }

    pub fn get(&self, idx: usize) -> Option<&T> {
        if idx >= self.length {
            return None;
        }

        let mut current_node = self.head.as_ref();
        let mut current_index = 0;

        while current_node.is_some() && current_index < idx {
            current_node = current_node.unwrap().next.as_ref();
            current_index += 1;
        }

        current_node.map(|node| &node.value)
    }

    pub fn remove_at(&mut self, idx: usize) -> Option<T> {
        if idx >= self.length {
            return None;
        }

        if idx == 0 {
            let value = self.head.take().unwrap().value;
            self.head = self.head.unwrap().next;
            self.length -= 1;
            return Some(value);
        }

        let mut current_node = self.head.as_mut();
        let mut prev_node: Option<&mut Box<ListNode<T>>> = None;
        let mut current_index = 0;

        while current_node.is_some() && current_index < idx {
            prev_node = current_node;
            current_node = current_node.unwrap().next.as_mut();
            current_index += 1;
        }

        if let Some(node) = current_node {
            prev_node.unwrap().next = node.next.take();
            self.length -= 1;
            return Some(node.value);
        }

        None
    }
}

