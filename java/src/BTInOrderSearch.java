package src;

import java.util.ArrayList;
import java.util.List;

public class BTInOrderSearch {

    public static class BinaryNode<T> {
        T value;
        BinaryNode<T> left;
        BinaryNode<T> right;

        public BinaryNode(T value, BinaryNode<T> left, BinaryNode<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private static void walk(BinaryNode<Integer> curr, List<Integer> path) {
        if (curr == null) {
            return;
        }

        // Recurse
        walk(curr.left, path);
        path.add(curr.value);
        walk(curr.right, path);
    }

    public static List<Integer> inOrderSearch(BinaryNode<Integer> head) {
        List<Integer> path = new ArrayList<>();
        walk(head, path);
        return path;
    }
}
