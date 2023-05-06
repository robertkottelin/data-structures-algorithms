use std::cell::RefCell;
use std::rc::Rc;

// Define a struct for tree nodes
#[derive(Debug)]
struct TreeNode {
    value: i32,
    left: Option<Rc<RefCell<TreeNode>>>,
    right: Option<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
    fn new(value: i32) -> TreeNode {
        TreeNode {
            value,
            left: None,
            right: None,
        }
    }

    fn add_child(&mut self, child: Rc<RefCell<TreeNode>>, is_left: bool) {
        if is_left {
            self.left = Some(child);
        } else {
            self.right = Some(child);
        }
    }

    fn remove_child(&mut self, is_left: bool) {
        if is_left {
            self.left = None;
        } else {
            self.right = None;
        }
    }
}

struct BinaryTree {
    root: Rc<RefCell<TreeNode>>,
}

impl BinaryTree {
    fn new(value: i32) -> BinaryTree {
        BinaryTree {
            root: Rc::new(RefCell::new(TreeNode::new(value))),
        }
    }
}


/*

Option: The Option enum is a generic Rust type that represents an optional value. 
It has two variants: Some(T) and None. It is used to express the possibility of the absence of a value. 
In the case of binary trees, using Option for the left and right children of a node allows us 
to express that a node might not have a left or right child.

Example usage:
let some_value = Some(42);
let no_value: Option<i32> = None;

Rc: The Rc (short for "reference counting") is a pointer type that provides shared ownership of a value of type T. 
It allows multiple parts of the code to share access to the same value without having to clone it. 
When the last reference to the value is dropped, the value will be deallocated. 
Rc is used for cases when you want to share data between multiple parts of your code but don't need to mutate it.

Example usage:
use std::rc::Rc;
let value = Rc::new(42);
let value_clone = Rc::clone(&value);

RefCell: The RefCell type is a mutable memory location with dynamically checked borrow rules. 
It allows you to have multiple mutable references to the same data as long as they are not active simultaneously 
(enforced by runtime borrow checking). This enables "interior mutability" - a pattern 
where you can mutate the contents of a value even when you only have an immutable reference to it. 
It's useful when you need to mutate data shared between different parts of your code but can ensure that only one part will mutate it at a time.

Example usage:
use std::cell::RefCell;
let value = RefCell::new(42);
let mut value_ref = value.borrow_mut();
*value_ref += 1;
In the binary tree implementation, we use Rc<RefCell<TreeNode>> to represent a shared, mutable reference to a tree node. This allows different parts of the tree to have access to the same nodes, and we can still mutate the tree when necessary (e.g., when adding or removing children).

*/