package src;

//  Implement a phone book using Trie.
// Data structure: Trie.java

class TrieNode {
    TrieNode[] children;
    boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}

class PhoneBook {
    TrieNode root;

    public PhoneBook() {
        root = new TrieNode();
    }

    public void insert(String name) {
        TrieNode node = root;
        for (int i = 0; i < name.length(); i++) {
            int index = name.charAt(i) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String name) {
        TrieNode node = root;
        for (int i = 0; i < name.length(); i++) {
            int index = name.charAt(i) - 'a';
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.isEndOfWord;
    }
}
