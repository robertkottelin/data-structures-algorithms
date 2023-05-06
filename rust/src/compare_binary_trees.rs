pub struct BinaryNode<T> {
    pub value: T,
    pub left: Option<Box<BinaryNode<T>>>,
    pub right: Option<Box<BinaryNode<T>>>,
}

pub fn compare<T: PartialEq>(a: Option<&Box<BinaryNode<T>>>, b: Option<&Box<BinaryNode<T>>>) -> bool {
    match (a, b) {
        (None, None) => true,
        (Some(node_a), Some(node_b)) => {
            node_a.value == node_b.value && compare(node_a.left.as_ref(), node_b.left.as_ref()) && compare(node_a.right.as_ref(), node_b.right.as_ref())
        }
        _ => false,
    }
}
