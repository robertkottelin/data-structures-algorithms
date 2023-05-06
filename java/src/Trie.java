package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
    boolean isEndOfWord;
    Map<String, TrieNode> children;

    public TrieNode() {
        isEndOfWord = false;
        children = new HashMap<>();
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String item) {
        TrieNode currentNode = this.root;

        for (char c : item.toCharArray()) {
            String charStr = String.valueOf(c);
            if (!currentNode.children.containsKey(charStr)) {
                currentNode.children.put(charStr, new TrieNode());
            }
            currentNode = currentNode.children.get(charStr);
        }

        currentNode.isEndOfWord = true;
    }

    public void delete(String item) {
        _delete(this.root, item, 0);
    }

    private boolean _delete(TrieNode node, String item, int index) {
        if (index == item.length()) {
            if (!node.isEndOfWord) {
                return false;
            }

            node.isEndOfWord = false;
            return node.children.size() == 0;
        }

        String charStr = String.valueOf(item.charAt(index));
        TrieNode childNode = node.children.get(charStr);

        if (childNode == null) {
            return false;
        }

        boolean shouldDeleteChild = _delete(childNode, item, index + 1);

        if (shouldDeleteChild) {
            node.children.remove(charStr);
            return node.children.size() == 0 && !node.isEndOfWord;
        }

        return false;
    }

    public List<String> find(String partial) {
        TrieNode currentNode = this.root;

        for (char c : partial.toCharArray()) {
            String charStr = String.valueOf(c);
            TrieNode childNode = currentNode.children.get(charStr);
            if (childNode == null) {
                return new ArrayList<>();
            }
            currentNode = childNode;
        }

        return _findWords(currentNode, partial);
    }

    private List<String> _findWords(TrieNode node, String prefix) {
        List<String> words = new ArrayList<>();

        if (node.isEndOfWord) {
            words.add(prefix);
        }

        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            String charStr = entry.getKey();
            TrieNode childNode = entry.getValue();
            List<String> childWords = _findWords(childNode, prefix + charStr);
            words.addAll(childWords);
        }

        return words;
    }
}

//////////////////////////////////////////////////////////////////////////////
/* 
// Implement Trie data structure for efficient string search and insertion.

class Trie {
    class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String key) {
        TrieNode currentNode = root;

        for (int level = 0; level < key.length(); level++) {
            int index = key.charAt(level) - 'a';
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }

            currentNode = currentNode.children[index];
        }

        currentNode.isEndOfWord = true;
    }

    public boolean search(String key) {
        TrieNode currentNode = root;

        for (int level = 0; level < key.length(); level++) {
            int index = key.charAt(level) - 'a';
            if (currentNode.children[index] == null) {
                return false;
            }

            currentNode = currentNode.children[index];
        }

        return currentNode != null && currentNode.isEndOfWord;
    }
}
*/