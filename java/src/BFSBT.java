package src;

import java.util.ArrayDeque;
import java.util.Deque;

//time complexity of BFS is O(n), where n is total number of nodes

public class BFSBT {

    // Definition for a binary tree node
    public static class BinaryNode<T> {
        public T value;
        public BinaryNode<T> left;
        public BinaryNode<T> right;

        public BinaryNode(T value, BinaryNode<T> left, BinaryNode<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Performs Breadth-First Search on a binary tree to check if a value exists.
     * @param head The root node of the binary tree.
     * @param needle The value to search for in the tree.
     * @return true if the value exists in the tree, false otherwise.
     */
    public static boolean bfs(BinaryNode<Integer> head, int needle) {
        // Create a new double-ended queue (Deque) to store nodes to be explored
        Deque<BinaryNode<Integer>> q = new ArrayDeque<>();

        // Add the root node (head) to the queue
        q.add(head);

        // Continue exploring nodes while the queue is not empty
        while (!q.isEmpty()) {
            // Remove the first node from the queue to explore
            BinaryNode<Integer> curr = q.removeFirst();

            // If the current node is null (e.g., a leaf node's child), skip it
            if (curr == null) {
                continue;
            }

            // If the current node's value is equal to the needle (search target), return true
            if (curr.value == needle) {
                return true;
            }

            // If the current node's left child is not null, add it to the end of the queue
            q.addLast(curr.left);

            // If the current node's right child is not null, add it to the end of the queue
            q.addLast(curr.right);
        }

        // If the loop finishes without finding the needle, return false
        return false;
    }

    public static void main(String[] args) {
        // Create a binary tree with the following structure:
        //        8
        //      /   \
        //     3     10
        //    / \      \
        //   1   6      14
        //      /  \    /
        //     4    7  13
        BinaryNode<Integer> root = new BinaryNode<>(8,
        new BinaryNode<>(3,
                new BinaryNode<>(1, null, null),
                new BinaryNode<>(6,
                        new BinaryNode<>(4, null, null),
                        new BinaryNode<>(7, null, null))),
        new BinaryNode<>(10,
                null,
                new BinaryNode<>(14,
                        new BinaryNode<>(13, null, null),
                        null)));


        // Call the bfs function to search for a value (e.g., 13) in the binary tree
        boolean found = bfs(root, 13);

        // Print the result of the search
        if (found) {
            System.out.println("The value 13 was found in the binary tree.");
        } else {
            System.out.println("The value 13 was not found in the binary tree.");
        }

        // Call the bfs function to search for a value (e.g., 25) not in the binary tree
        found = bfs(root, 25);

        // Print the result of the search
        if (found) {
            System.out.println("The value 25 was found in the binary tree.");
        } else {
            System.out.println("The value 25 was not found in the binary tree.");
        }
    }

}


//////////////////////////////////////////////////////////
// Implement a Binary Search Tree with insertion, deletion, and traversal methods.

class BinarySearchTree {
    class Node {
        int key;
        Node left, right;

        public Node(int key) {
            this.key = key;
            left = right = null;
        }
    }

    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }

        return root;
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    private int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }
}

