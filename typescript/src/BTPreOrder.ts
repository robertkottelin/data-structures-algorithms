function walk(curr: BinaryNode<number> | null, path: number[]): void {
    if (!curr) {
        return;
    }

    // recurse
    // pre
    path.push(curr.value);
    // recurse
    walk(curr.left, path);
    walk(curr.right, path);
    // post, not needed
}

export default function pre_order_search(head: BinaryNode<number>): number[] {
    const path: number[] = [];
    walk(head, path);
    return path;
}