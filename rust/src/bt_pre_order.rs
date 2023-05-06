pub struct BinaryNode<T> {
    pub value: T,
    pub left: Option<Box<BinaryNode<T>>>,
    pub right: Option<Box<BinaryNode<T>>>,
}

fn walk<T>(curr: Option<&Box<BinaryNode<T>>>, path: &mut Vec<T>)
where
    T: Copy,
{
    if let Some(node) = curr {
        path.push(node.value);
        walk(node.left.as_ref(), path);
        walk(node.right.as_ref(), path);
    }
}

pub fn pre_order_search(head: &BinaryNode<i32>) -> Vec<i32> {
    let mut path: Vec<i32> = Vec::new();
    walk(head.left.as_ref().or(head.right.as_ref()), &mut path);
    path.push(head.value);
    path
}
