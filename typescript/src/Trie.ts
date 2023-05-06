type TrieNode = {
    isEndOfWord: boolean;
    children: Map<string, TrieNode>;
}

export default class Trie {
    private root: TrieNode;

    constructor() {
        this.root = { isEndOfWord: false, children: new Map() };
    }

    insert(item: string): void {
        let currentNode = this.root;

        for (const char of item) {
            if (!currentNode.children.has(char)) {
                currentNode.children.set(char, { isEndOfWord: false, children: new Map() });
            }
            currentNode = currentNode.children.get(char)!;
        }

        currentNode.isEndOfWord = true;
    }

    delete(item: string): void {
        this._delete(this.root, item, 0);
    }

    private _delete(node: TrieNode, item: string, index: number): boolean {
        if (index === item.length) {
            if (!node.isEndOfWord) {
                return false;
            }

            node.isEndOfWord = false;
            return node.children.size === 0;
        }

        const char = item[index];
        const childNode = node.children.get(char);

        if (!childNode) {
            return false;
        }

        const shouldDeleteChild = this._delete(childNode, item, index + 1);

        if (shouldDeleteChild) {
            node.children.delete(char);
            return node.children.size === 0 && !node.isEndOfWord;
        }

        return false;
    }

    find(partial: string): string[] {
        let currentNode = this.root;

        for (const char of partial) {
            const childNode = currentNode.children.get(char);
            if (!childNode) {
                return [];
            }
            currentNode = childNode;
        }

        return this._findWords(currentNode, partial);
    }

    private _findWords(node: TrieNode, prefix: string): string[] {
        const words: string[] = [];

        if (node.isEndOfWord) {
            words.push(prefix);
        }

        for (const [char, childNode] of node.children) {
            const childWords = this._findWords(childNode, prefix + char);
            words.push(...childWords);
        }

        return words;
    }
}
