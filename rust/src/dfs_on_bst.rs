pub struct BinaryNode {
    pub value: i32,
    pub left: Option<Box<BinaryNode>>,
    pub right: Option<Box<BinaryNode>>,
}

fn search(curr: Option<&Box<BinaryNode>>, needle: i32) -> bool {
    match curr {
        None => false,
        Some(node) => {
            if node.value == needle {
                true
            } else if node.value < needle {
                search(node.right.as_ref(), needle)
            } else {
                search(node.left.as_ref(), needle)
            }
        }
    }
}

pub fn dfs(head: Option<Box<BinaryNode>>, needle: i32) -> bool {
    search(head.as_ref(), needle)
}
