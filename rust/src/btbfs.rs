pub struct BinaryNode<T> {
    pub value: T,
    pub left: Option<Box<BinaryNode<T>>>,
    pub right: Option<Box<BinaryNode<T>>>,
}

pub fn bfs(head: &BinaryNode<i32>, needle: i32) -> bool {
    let mut q: Vec<Option<&BinaryNode<i32>>> = vec![Some(head)];

    while !q.is_empty() {
        let curr = q.remove(0);

        if let Some(node) = curr {
            if node.value == needle {
                return true;
            }

            if let Some(left) = &node.left {
                q.push(Some(left));
            }
            if let Some(right) = &node.right {
                q.push(Some(right));
            }
        }
    }

    false
}
