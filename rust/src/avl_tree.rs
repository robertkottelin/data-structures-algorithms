use std::cmp::{Ord, Ordering};
use std::mem;

#[derive(Debug)]
pub struct AvlTree<T: Ord> {
    root: Option<Box<Node<T>>>,
}

#[derive(Debug)]
struct Node<T: Ord> {
    value: T,
    left: Option<Box<Node<T>>>,
    right: Option<Box<Node<T>>>,
    height: usize,
}

impl<T: Ord> AvlTree<T> {
    pub fn new() -> Self {
        Self { root: None }
    }

    pub fn insert(&mut self, value: T) {
        self.root = self.insert_node(self.root.take(), value);
    }

    fn insert_node(&mut self, node: Option<Box<Node<T>>>, value: T) -> Option<Box<Node<T>>> {
        match node {
            None => Some(Box::new(Node::new(value))),
            Some(mut n) => {
                match value.cmp(&n.value) {
                    Ordering::Less => {
                        n.left = self.insert_node(n.left.take(), value);
                    }
                    Ordering::Greater => {
                        n.right = self.insert_node(n.right.take(), value);
                    }
                    Ordering::Equal => return Some(n),
                }
                n.update_height();
                Some(Self::balance(n))
            }
        }
    }

    pub fn remove(&mut self, value: &T) {
        self.root = self.remove_node(self.root.take(), value);
    }

    fn remove_node(&mut self, node: Option<Box<Node<T>>>, value: &T) -> Option<Box<Node<T>>> {
        if let Some(mut n) = node {
            match value.cmp(&n.value) {
                Ordering::Less => {
                    n.left = self.remove_node(n.left.take(), value);
                }
                Ordering::Greater => {
                    n.right = self.remove_node(n.right.take(), value);
                }
                Ordering::Equal => {
                    if n.left.is_none() {
                        return n.right;
                    } else if n.right.is_none() {
                        return n.left;
                    }

                    let min_right = Self::find_min(&mut n.right);
                    n.value = mem::replace(&mut min_right.value, n.value);
                    n.right = self.remove_node(n.right.take(), &n.value);
                }
            }

            n.update_height();
            Some(Self::balance(n))
        } else {
            None
        }
    }

    fn find_min(node: &mut Option<Box<Node<T>>>) -> &mut Node<T> {
        let mut current = node.as_mut().unwrap();
        while let Some(ref mut left) = current.left {
            current = left;
        }
        current
    }

    fn balance(mut node: Box<Node<T>>) -> Box<Node<T>> {
        let balance_factor = node.balance_factor();

        if balance_factor > 1 {
            if node.left.as_ref().unwrap().balance_factor() < 0 {
                node.left = Some(Self::rotate_left(node.left.take().unwrap()));
            }
            return Self::rotate_right(node);
        }

        if balance_factor < -1 {
            if node.right.as_ref().unwrap().balance_factor() > 0 {
                node.right = Some(Self::rotate_right(node.right.take().unwrap()));
            }
            return Self::rotate_left(node);
        }

        node
    }

    fn rotate_left(mut root: Box<Node<T>>) -> Box<Node<T>> {
        let mut new_root = root.right.take().unwrap();
        root.right = new_root.left.take();
        root.update_height();
        new_root.left = Some(root);
        new_root.update_height();
        new_root
    }

    fn rotate_right(mut root: Box<Node<T>>) -> Box<Node<T>> {
        let mut new_root = root.left.take().unwrap();
        root.left = new_root.right.take();
        root.update_height();
        new_root.right = Some(root);
        new_root.update_height();
        new_root
    }
}

impl<T: Ord> Node<T> {
    fn new(value: T) -> Self {
        Self {
            value,
            left: None,
            right: None,
            height: 1,
        }
    }

    fn update_height(&mut self) {
        let left_height = self.left.as_ref().map_or(0, |n| n.height);
        let right_height = self.right.as_ref().map_or(0, |n| n.height);
        self.height = 1 + std::cmp::max(left_height, right_height);
    }

    fn balance_factor(&self) -> isize {
        let left_height = self.left.as_ref().map_or(0, |n| n.height) as isize;
        let right_height = self.right.as_ref().map_or(0, |n| n.height) as isize;
        left_height - right_height
    }
}

// fn main() {
//     let mut tree = AvlTree::new();
//     tree.insert(10);
//     tree.insert(20);
//     tree.insert(30);
//     tree.insert(40);
//     tree.insert(50);
//     tree.insert(25);

//     println!("{:?}", tree);
    
//     tree.remove(&40);
//     println!("{:?}", tree);
// }


