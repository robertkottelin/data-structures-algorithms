function walk(curr: BinaryNode<number> | null, path: number[]): void {
    if (!curr) {
        return;
    }

    // recurse
    // pre
    
    // recurse
    walk(curr.left, path);
    walk(curr.right, path);
    path.push(curr.value);
    // post, not needed
}

export default function post_order_search(head: BinaryNode<number>): number[] {
    const path: number[] = [];
    walk(head, path);
    return path;
}