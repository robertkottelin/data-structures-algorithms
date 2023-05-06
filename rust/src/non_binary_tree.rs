use std::cell::RefCell;
use std::rc::Rc;

// Define a struct for tree nodes
#[derive(Debug)]
struct TreeNode {
    value: i32,
    children: Vec<Rc<RefCell<TreeNode>>>,
}

impl TreeNode {
    fn new(value: i32) -> TreeNode {
        TreeNode {
            value,
            children: Vec::new(),
        }
    }

    fn add_child(&mut self, child: Rc<RefCell<TreeNode>>) {
        self.children.push(child);
    }

    fn remove_child(&mut self, child_to_remove: &Rc<RefCell<TreeNode>>) {
        self.children
            .retain(|child| !Rc::ptr_eq(child, child_to_remove));
    }
}

struct NonBinaryTree {
    root: Rc<RefCell<TreeNode>>,
}

impl NonBinaryTree {
    fn new(value: i32) -> NonBinaryTree {
        NonBinaryTree {
            root: Rc::new(RefCell::new(TreeNode::new(value))),
        }
    }
}
