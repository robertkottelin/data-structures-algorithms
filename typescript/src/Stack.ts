// last in, first out

type Node<T> = {
    value?: T,
    prev?: Node<T>,
}

export default class Stack<T> {
    public length: number;
    private head?: Node<T>;
    
    constructor() {
        this.head = undefined;
        this.length = 0;
    }

    push(item: T): void {
        const node = {value: item} as Node<T>;

        this.length ++;
        if (!this.head) {
            this.head = node;
            return;
        }
        node.prev = this.head;
        this.head = node;
    }

    pop(): T | undefined {
        this.length = Math.max(0, this.length - 1);
        if (this.length === 0) {
            const head = this.head;
            this.head = undefined;
            return head?.value;
        }

        const head  = this.head as Node<T>;
        this.head = head.prev;

        // // free memory
        // head.prev = undefined;

        return head.value;
    }

    peek(): T | undefined {
        return this.head?.value;
    }
}

// RUST
// struct Node<T> {
//     value: Option<T>,
//     prev: Option<Box<Node<T>>>,
// }

// impl<T> Node<T> {
//     fn new(value: T) -> Self {
//         Node { value: Some(value), prev: None }
//     }
// }

// pub struct Stack<T> {
//     length: usize,
//     head: Option<Box<Node<T>>>,
// }

// impl<T> Stack<T> {
//     pub fn new() -> Self {
//         Stack { length: 0, head: None }
//     }

//     pub fn push(&mut self, item: T) {
//         let node = Box::new(Node::new(item));

//         self.length += 1;
//         if self.head.is_none() {
//             self.head = Some(node);
//             return;
//         }
//         let mut head = self.head.take().unwrap();
//         head.prev = Some(node);
//         self.head = Some(head);
//     }

//     pub fn pop(&mut self) -> Option<T> {
//         self.length = self.length.saturating_sub(1);
//         if self.length == 0 {
//             let head = self.head.take();
//             return head.unwrap().value;
//         }

//         let mut head = self.head.take().unwrap();
//         self.head = head.prev.take();

//         // // free memory
//         // head.prev = None;

//         head.value
//     }

//     pub fn peek(&self) -> Option<&T> {
//         self.head.as_ref().map(|head| head.value.as_ref().unwrap())
//     }
// }