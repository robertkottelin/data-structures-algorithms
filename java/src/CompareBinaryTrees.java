package src;

public class CompareBinaryTrees {

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

    public static boolean compare(BinaryNode<Integer> a, BinaryNode<Integer> b) {
        // Base case 1: Structural check
        if (a == null && b == null) {
            return true;
        }

        // Base case 2: Structural check
        if (a == null || b == null) {
            return false;
        }

        // Base case 3: Value check
        if (!a.value.equals(b.value)) {
            return false;
        }

        return compare(a.left, b.left) && compare(a.right, b.right);
    }
}
