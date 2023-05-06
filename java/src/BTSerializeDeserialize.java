package src;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BTSerializeDeserialize {
    private static final String separator = ",";
    private static final String nullNode = "null";

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(nullNode).append(separator);
        } else {
            sb.append(node.val).append(separator); // node.val is an integer, so it's automatically converted to a string when appended to the StringBuilder
            serialize(node.left, sb);
            serialize(node.right, sb);
        }
    }

    public TreeNode deserialize(String data) {
        // Use LinkedList<String> to explicitly specify the type argument for LinkedList
        LinkedList<String> nodes = new LinkedList<String>(Arrays.asList(data.split(separator)));
        return deserialize(nodes);
    }

    private TreeNode deserialize(LinkedList<String> nodes) {
        String value = nodes.poll();
        if (value.equals(nullNode)) {
            return null;
        }

        // Convert the string value to an integer using Integer.parseInt(value) instead of Integer.valueOf(value)
        TreeNode node = new TreeNode(Integer.parseInt(value));
        node.left = deserialize(nodes);
        node.right = deserialize(nodes);

        return node;
    }
}
