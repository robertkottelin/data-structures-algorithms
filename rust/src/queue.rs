// Import necessary types from the standard library
use std::rc::Rc;
use std::cell::RefCell;

// Define the structure for a single node in the queue
#[derive(Debug)]
struct Node<T> {
    value: T, // The value stored in the node
    next: Option<Rc<RefCell<Node<T>>>>, // The next node in the queue (None if it's the last node)
}

// Define the structure for the queue
#[derive(Debug)]
pub struct Queue<T> {
    length: usize, // The number of elements in the queue
    head: Option<Rc<RefCell<Node<T>>>>, // The first element in the queue (None if it's empty)
    tail: Option<Rc<RefCell<Node<T>>>>, // The last element in the queue (None if it's empty)
}

// Implement methods for the queue
impl<T: Clone> Queue<T> {
    // Create a new, empty queue
    pub fn new() -> Self {
        Queue {
            length: 0,
            head: None,
            tail: None,
        }
    }

    // Add an element to the end of the queue
    pub fn enqueue(&mut self, item: T) {
        // Create a new node with the given value and no next node
        let new_node = Rc::new(RefCell::new(Node {
            value: item,
            next: None,
        }));

        // Increment the length of the queue
        self.length += 1;

        // If there is a tail, update its next node to the new node
        if let Some(tail) = &self.tail {
            tail.borrow_mut().next = Some(new_node.clone());
        } else {
            // If there's no tail, this is the first element in the queue, so set the head
            self.head = Some(new_node.clone());
        }

        // Update the tail to the new node
        self.tail = Some(new_node);
    }

    // Remove and return the first element from the queue, if it exists
    pub fn deque(&mut self) -> Option<T> {
        // If the queue is empty, return None
        if self.head.is_none() {
            return None;
        }

        // Decrement the length of the queue
        self.length -= 1;

        // Remove the head from the queue
        let head = self.head.take().unwrap();
        // Set the new head to the next node in the queue
        self.head = head.borrow_mut().next.clone();

        // If the new head is None, the queue is now empty, so set the tail to None as well
        if self.head.is_none() {
            self.tail = None;
        }

        // Return the value of the removed head
        Some(Rc::try_unwrap(head).ok().unwrap().into_inner().value)
    }

    // Return the value of the first element in the queue, if it exists
    pub fn peek(&self) -> Option<T> {
        // If there is a head, return a clone of its value
        self.head.as_ref().map(|head| head.borrow().value.clone())
    }
}
