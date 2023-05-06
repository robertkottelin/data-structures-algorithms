use std::cmp::Ordering;
use std::rc::Rc;

pub struct Node<T> {
    value: T,
    next: Option<Box<Node<T>>>,
}

pub struct PriorityQueue<T, F>
where
    T: PartialOrd,
    F: Fn(&T, &T) -> Ordering,
{
    head: Option<Box<Node<T>>>,
    size: usize,
    comparator: Rc<F>,
}

impl<T, F> PriorityQueue<T, F>
where
    T: PartialOrd,
    F: Fn(&T, &T) -> Ordering,
{
    // Constructor with custom comparator for priority ordering
    pub fn new(comparator: F) -> Self {
        Self {
            head: None,
            size: 0,
            comparator: Rc::new(comparator),
        }
    }

    // Compare two items using the provided comparator
    fn compare(&self, item1: &T, item2: &T) -> Ordering {
        (self.comparator)(item1, item2)
    }

    // Enqueue an item into the priority queue
    pub fn enqueue(&mut self, item: T) {
        let new_node = Box::new(Node {
            value: item,
            next: None,
        });

        if self.head.is_none() || self.compare(&new_node.value, &self.head.as_ref().unwrap().value) == Ordering::Greater {
            new_node.next = self.head.take();
            self.head = Some(new_node);
        } else {
            let mut current_node = self.head.as_mut().unwrap();

            while current_node.next.is_some()
                && self.compare(&new_node.value, &current_node.next.as_ref().unwrap().value) != Ordering::Greater
            {
                current_node = current_node.next.as_mut().unwrap();
            }

            new_node.next = current_node.next.take();
            current_node.next = Some(new_node);
        }

        self.size += 1;
    }

    // Dequeue the highest priority item from the priority queue
    pub fn dequeue(&mut self) -> Option<T> {
        self.head.take().map(|old_head| {
            self.head = old_head.next;
            self.size -= 1;
            old_head.value
        })
    }

    // Peek at the highest priority item without removing it
    pub fn peek(&self) -> Option<&T> {
        self.head.as_ref().map(|head| &head.value)
    }

    // Get the size of the priority queue
    pub fn size(&self) -> usize {
        self.size
    }
}


// USAGE example with integers:
// In this Rust implementation, the PriorityQueue struct accepts a custom comparator closure with a reference-counted pointer Rc to avoid multiple ownership issues. If you want to use the natural order for a type implementing Ord, you can pass std::cmp::Ord::cmp as the comparator function.
// fn main() {
//     let custom_comparator = |a: &i32, b: &i32| b.cmp(a);
//     let mut priority_queue = PriorityQueue::new(custom_comparator);

//     priority_queue.enqueue(3);
//     priority_queue.enqueue(1);
//     priority_queue.enqueue(2);

//     println!("Dequeued: {:?}", priority_queue.dequeue()); // 3
//     println!("Peek: {:?}", priority_queue.peek()); // 2
//     println!("Size: {}", priority_queue.size()); // 2
// }
