package src;

// Implement a binary search tree data structure and its basic operations (insert, search, delete).

public class BinarySearchTree {

    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
    
    TreeNode root;
    
    public BinarySearchTree() {
        root = null;
    }
    
    public void insert(int value) {
        root = insertRec(root, value);
    }
    
    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
    
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }
    
        return root;
    }
    
    public boolean search(int value) {
        return searchRec(root, value);
    }
    
    private boolean searchRec(TreeNode root, int value) {
        if (root == null) {
            return false;
        }
    
        if (root.value == value) {
            return true;
        }
    
        if (value < root.value) {
            return searchRec(root.left, value);
        } else {
            return searchRec(root.right, value);
        }
    }
    
    public void delete(int value) {
        root = deleteRec(root, value);
    }
    
    private TreeNode deleteRec(TreeNode root, int value) {
        if (root == null) {
            return root;
        }
    
        if (value < root.value) {
            root.left = deleteRec(root.left, value);
        } else if (value > root.value) {
            root.right = deleteRec(root.right, value);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
    
            root.value = minValue(root.right);
            root.right = deleteRec(root.right, root.value);
        }
    
        return root;
    }
    
    private int minValue(TreeNode root) {
        int minValue = root.value;
        while (root.left != null) {
            minValue = root.left.value;
            root = root.left;
        }
        return minValue;
    }
}
           
