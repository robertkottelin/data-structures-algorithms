// first in first out, if the queue is empty

type Node<T> = {
    value: T,
    next?: Node<T>,
}

export default class Queue<T> {
    public length: number;
    private head?: Node<T>;
    private tail?: Node<T>;

    constructor() {
        this.head = this.tail = undefined;
        this.length = 0;
    }

    enqueue(item: T): void {
        const node = {value: item} as Node<T>;
        this.length ++;
        if (!this.tail) {
            this.tail = this.head = node;
            return;
        }

        this.tail.next  = node;
        this.tail = node;
    }

    deque(): T | undefined {
        if (!this.head) {
            return undefined;
        }

        this.length --;
        
        const head = this.head;
        this.head = this.head.next;

        head.next = undefined;

        return head.value;
    }

    peek(): T | undefined {
        return this.head?.value;
    }
}


// interface LinkedList<T> {
//     get length(): number;
//     insertAt(item: T, index: number): void;
//     remove(item: T): T | undefined;
//     removeAt(index: number): T | undefined;
//     append(item: T): void;
//     prepend(item: T): void;
//     get(index: number): T | undefined;
// }


// RUST
// #[derive(Debug)]
// struct Node<T> {
//     value: T,
//     next: Option<Box<Node<T>>>,
// }

// #[derive(Debug)]
// pub struct Queue<T> {
//     length: usize,
//     head: Option<Box<Node<T>>>,
//     tail: Option<Box<Node<T>>>,
// }

// impl<T> Queue<T> {
//     pub fn new() -> Self {
//         Queue {
//             length: 0,
//             head: None,
//             tail: None,
//         }
//     }

//     pub fn enqueue(&mut self, item: T) {
//         let node = Node {
//             value: item,
//             next: None,
//         };
//         self.length += 1;
//         if self.tail.is_none() {
//             self.tail = Some(Box::new(node));
//             self.head = self.tail.clone();
//             return;
//         }

//         self.tail.as_mut().unwrap().next = Some(Box::new(node));
//         self.tail = self.tail.as_mut().unwrap().next.clone();
//     }

//     pub fn deque(&mut self) -> Option<T> {
//         if self.head.is_none() {
//             return None;
//         }

//         self.length -= 1;

//         let head = self.head.take();
//         self.head = head.as_ref().unwrap().next.clone();

//         Some(head.unwrap().value)
//     }

//     pub fn peek(&self) -> Option<&T> {
//         self.head.as_ref().map(|head| &head.value)
//     }
// }