type Node<T> = {
    value: T,
    next?: Node<T>,
    prev?: Node<T>,
}

export default class DoublyLinkedList<T> {
    public length: number;
    private head?: Node<T>;    
    private tail?: Node<T>;

    constructor() {
        this.length = 0;
        this.head = undefined;
        this.tail = undefined;
    }

    prepend(item: T): void {
        const node = {value: item} as Node<T>;
        this.length ++;
        if (!this.head) {
            this.head = this.tail = node;
            return;
        }

        node.next = this.head;
        this.head.prev = node;
        this.head = node;

    }
    insertAt(item: T, idx: number): void {
        if (idx > this.length) {
            throw new Error('Index out of bounds');
        } else if (idx === this.length) {
            this.append(item);
            return;
        } else if (idx === 0) {
            this.prepend(item);
            return;
        }
        this.length ++;
        let curr = this.head;
        for (let i = 0; curr && i < idx; ++i) {
            curr = curr.next;
        }

        curr = curr as Node<T>;
        const node = {value: item} as Node<T>;
        node.next = curr;
        node.prev = curr.prev;
        curr.prev = node;

        if (curr.prev) {
            curr.prev.next = node;
        }
    }

    append(item: T): void {
        this.length ++;
        const node = {value: item} as Node<T>;
        if (!this.tail) {
            this.head = this.tail = node;
            return;
        }
        node.prev = this.tail;
        this.tail.next = node;
        this.tail = node;
    }

    remove(item: T): T | undefined {
        let curr = this.head;
        while (curr && curr.value !== item) {
            curr = curr.next;
        }

        if (!curr) {
            return undefined;
        }

        this.length --;

        if (this.length === 0) {
            const out = this.head?.value;
            this.head = this.tail = undefined;
            return out;
        }

        if (curr.prev) {
            curr.prev.next = curr.next;
        }

        if (curr.next) {
            curr.next.prev = curr.prev;
        }

        if (curr === this.head) {
            this.head = curr.next;
        }

        if (curr === this.tail) {
            this.tail = curr.prev;
        }

        curr.prev = curr.next = undefined;

        return curr.value;
    }

    get(idx: number): T | undefined {
        let curr = this.head;
        for (let i =    0; i < idx && curr; ++i) {
            curr = curr.next;
        }
    
        return curr?.value;
    
    }
    
    removeAt(idx: number): T | undefined {
        const node = this.getAt(idx);
    
        if (!node) {
            return undefined;
        }
    
        return this.removeNode(node);
    }
    
    private removeNode(node: Node<T>): T | undefined {
        if (!node) {
            return undefined;
        }
    
        this.length--;
    
        if (node.prev) {
            node.prev.next = node.next;
        }
    
        if (node.next) {
            node.next.prev = node.prev;
        }
    
        if (node === this.head) {
            this.head = node.next;
        }
    
        if (node === this.tail) {
            this.tail = node.prev;
        }
    
        node.prev = node.next = undefined;
    
        return node.value;
    }
    
    private getAt(idx: number): Node<T> | undefined {
        let curr = this.head;
        for (let i = 0; i < idx && curr; ++i) {
            curr = curr.next;
        }
        return curr;
    }
}

// RUST
// pub struct Node<T> {
//     value: T,
//     next: Option<Box<Node<T>>>,
//     prev: Option<Box<Node<T>>>,
// }

// pub struct DoublyLinkedList<T> {
//     length: usize,
//     head: Option<Box<Node<T>>>,
//     tail: Option<Box<Node<T>>>,
// }

// impl<T> DoublyLinkedList<T> {
//     pub fn new() -> Self {
//         DoublyLinkedList {
//             length: 0,
//             head: None,
//             tail: None,
//         }
//     }

//     pub fn prepend(&mut self, item: T) {
//         let mut node = Box::new(Node {
//             value: item,
//             next: self.head.take(),
//             prev: None,
//         });

//         self.length += 1;

//         if let Some(ref mut head) = node.next {
//             head.prev = Some(node.clone());
//         } else {
//             self.tail = Some(node.clone());
//         }

//         self.head = Some(node);
//     }

//     pub fn insert_at(&mut self, item: T, idx: usize) {
//         if idx > self.length {
//             panic!("Index out of bounds");
//         } else if idx == self.length {
//             self.append(item);
//             return;
//         } else if idx == 0 {
//             self.prepend(item);
//             return;
//         }

//         self.length += 1;
//         let mut curr = self.head.as_mut();

//         for _ in 0..idx {
//             curr = curr.unwrap().next.as_mut();
//         }

//         let mut node = Box::new(Node {
//             value: item,
//             prev: curr.as_mut().unwrap().prev.take(),
//             next: curr.take(),
//         });

//         if let Some(ref mut prev) = node.prev {
//             prev.next = Some(node.clone());
//         }

//         if let Some(ref mut next) = node.next {
//             next.prev = Some(node.clone());
//         }

//         curr.replace(node);
//     }

//     pub fn append(&mut self, item: T) {
//         let mut node = Box::new(Node {
//             value: item,
//             next: None,
//             prev: self.tail.take(),
//         });

//         self.length += 1;

//         if let Some(ref mut tail) = node.prev {
//             tail.next = Some(node.clone());
//         } else {
//             self.head = Some(node.clone());
//         }

//         self.tail = Some(node);
//     }

//     pub fn remove(&mut self, item: T) -> Option<T>
//     where
//         T: PartialEq,
//     {
//         let mut curr = self.head.as_mut();

//         while let Some(ref mut node) = curr {
//             if node.value == item {
//                 break;
//             }
//             curr = node.next.as_mut();
//         }

//         curr.map(|node| self.remove_node(node))
//     }

//     pub fn get(&self, idx: usize) -> Option<&T> {
//         let mut curr = self.head.as_ref();

//         for _ in 0..idx {
//             curr = curr?.next.as_ref();
//         }

//         curr.map(|node| &node.value)
//     }

//     pub fn remove_at(&mut self, idx: usize) -> Option<T> {
//         let node = self.get_node_at(idx)?;
//         self.remove_node(node)
//     }

//     fn remove_node(&mut self, node: &mut Node<T>) -> Option<T> {
//         self.length -= 1;

//         if let Some(ref mut prev) = node.prev.as_mut() {
//             prev.next = node.next.take();
//         }

//         if let Some(ref mut next) = node.next.as_mut() {
//             next.prev = node.prev.take();
//         }

//         if node.prev.is_none() {
//             self.head = node.next.take();
//         }
//         if node.next.is_none() {
//             self.tail = node.prev.take();
//         }
    
//         node.prev = None;
//         node.next = None;
    
//         Some(node.value.clone())
//     }
    
//     fn get_node_at(&mut self, idx: usize) -> Option<&mut Node<T>> {
//         let mut curr = self.head.as_mut();
    
//         for _ in 0..idx {
//             curr = curr?.next.as_mut();
//         }
    
//         curr
//     }
// }    
