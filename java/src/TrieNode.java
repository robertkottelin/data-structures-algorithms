package src;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    boolean isEndOfWord;
    Map<String, TrieNode> children;

    public TrieNode() {
        isEndOfWord = false;
        children = new HashMap<>();
    }
}
