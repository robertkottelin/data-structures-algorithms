use std::collections::HashMap;

#[derive(Default)]
struct TrieNode {
    is_end_of_word: bool,
    children: HashMap<char, TrieNode>,
}

pub struct Trie {
    root: TrieNode,
}

impl Trie {
    pub fn new() -> Self {
        Trie {
            root: TrieNode::default(),
        }
    }

    pub fn insert(&mut self, item: &str) {
        let mut current_node = &mut self.root;

        for ch in item.chars() {
            current_node = current_node.children.entry(ch).or_insert_with(TrieNode::default);
        }

        current_node.is_end_of_word = true;
    }

    pub fn delete(&mut self, item: &str) {
        self._delete(&mut self.root, item, 0);
    }

    fn _delete(&mut self, node: &mut TrieNode, item: &str, index: usize) -> bool {
        if index == item.len() {
            if !node.is_end_of_word {
                return false;
            }

            node.is_end_of_word = false;
            return node.children.is_empty();
        }

        let ch = item.chars().nth(index).unwrap();
        let child_node = node.children.get_mut(&ch);

        if let Some(child) = child_node {
            let should_delete_child = self._delete(child, item, index + 1);

            if should_delete_child {
                node.children.remove(&ch);
                return node.children.is_empty() && !node.is_end_of_word;
            }
        }

        false
    }

    pub fn find(&self, partial: &str) -> Vec<String> {
        let mut current_node = &self.root;

        for ch in partial.chars() {
            let child_node = current_node.children.get(&ch);
            if let Some(child) = child_node {
                current_node = child;
            } else {
                return vec![];
            }
        }

        self._find_words(current_node, partial)
    }

    fn _find_words(&self, node: &TrieNode, prefix: &str) -> Vec<String> {
        let mut words: Vec<String> = vec![];

        if node.is_end_of_word {
            words.push(prefix.to_owned());
        }

        for (ch, child_node) in &node.children {
            let child_words = self._find_words(child_node, &(prefix.to_owned() + &ch.to_string()));
            words.extend(child_words);
        }

        words
    }
}
