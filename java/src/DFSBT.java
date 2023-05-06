package src;

public class DFSBT {

    // Definition for a binary tree node
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

    /**
     * Recursively searches for a value in a binary tree using Depth-First Search.
     * @param curr The current node being explored.
     * @param needle The value to search for in the tree.
     * @return true if the value exists in the tree, false otherwise.
     */
    private static boolean search(BinaryNode<Integer> curr, int needle) {
        if (curr == null) {
            return false;
        }

        if (curr.value == needle) {
            return true;
        }

        if (curr.value < needle) {
            return search(curr.right, needle);
        }

        if (curr.value > needle) {
            return search(curr.left, needle);
        }

        return search(curr.left, needle) || search(curr.right, needle);
    }

    /**
     * Initiates the Depth-First Search on a binary tree to check if a value exists.
     * @param head The root node of the binary tree.
     * @param needle The value to search for in the tree.
     * @return true if the value exists in the tree, false otherwise.
     */
    public static boolean dfs(BinaryNode<Integer> head, int needle) {
        return search(head, needle);
    }
}
