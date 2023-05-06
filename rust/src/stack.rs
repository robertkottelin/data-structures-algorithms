// last in, first out

struct Node<T> {
    value: Option<T>,
    prev: Option<Box<Node<T>>>,
}

impl<T> Node<T> {
    fn new(value: T) -> Self {
        Node { value: Some(value), prev: None }
    }
}

pub struct Stack<T> {
    length: usize,
    head: Option<Box<Node<T>>>,
}

impl<T> Stack<T> {
    pub fn new() -> Self {
        Stack { length: 0, head: None }
    }

    pub fn push(&mut self, item: T) {
        let node = Box::new(Node::new(item));

        self.length += 1;
        if self.head.is_none() {
            self.head = Some(node);
            return;
        }
        let mut head = self.head.take().unwrap();
        head.prev = Some(node);
        self.head = Some(head);
    }

    pub fn pop(&mut self) -> Option<T> {
        self.length = self.length.saturating_sub(1);
        if self.length == 0 {
            let head = self.head.take();
            return head.unwrap().value;
        }

        let mut head = self.head.take().unwrap();
        self.head = head.prev.take();

        // // free memory
        // head.prev = None;

        head.value
    }

    pub fn peek(&self) -> Option<&T> {
        self.head.as_ref().map(|head| head.value.as_ref().unwrap())
    }
}