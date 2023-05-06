type ListNode<T> = {
    value: T,
    next?: ListNode<T>,
}

export default class SinglyLinkedList<T> {
    public length: number;
    private head?: ListNode<T>;

    constructor() {
        this.length = 0;
    }

    prepend(item: T): void {
        const node = { value: item, next: this.head } as ListNode<T>;
        this.head = node;
        this.length++;
    }

    insertAt(item: T, idx: number): void {
        if (idx < 0 || idx > this.length) {
            throw new Error('Index out of bounds');
        }

        if (idx === 0) {
            this.prepend(item);
            return;
        }

        let currentNode = this.head;
        let prevNode: ListNode<T> | undefined;
        let currentIndex = 0;

        while (currentIndex < idx) {
            prevNode = currentNode;
            currentNode = currentNode?.next;
            currentIndex++;
        }

        const newNode = { value: item, next: currentNode } as ListNode<T>;
        prevNode!.next = newNode;
        this.length++;
    }

    append(item: T): void {
        if (!this.head) {
            this.prepend(item);
            return;
        }

        let currentNode = this.head;
        while (currentNode.next) {
            currentNode = currentNode.next;
        }

        currentNode.next = { value: item } as ListNode<T>;
        this.length++;
    }

    remove(item: T): T | undefined {
        if (!this.head) {
            return undefined;
        }

        let currentNode = this.head;
        let prevNode: ListNode<T> | undefined;

        if (currentNode.value === item) {
            this.head = currentNode.next;
            this.length--;
            return item;
        }

        while (currentNode.next && currentNode.value !== item) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        if (currentNode.value === item) {
            prevNode!.next = currentNode.next;
            this.length--;
            return item;
        }

        return undefined;
    }

    get(idx: number): T | undefined {
        if (idx < 0 || idx >= this.length) {
            return undefined;
        }

        let currentNode = this.head;
        let currentIndex = 0;

        while (currentNode && currentIndex < idx) {
            currentNode = currentNode.next;
            currentIndex++;
        }

        return currentNode?.value;
    }

    removeAt(idx: number): T | undefined {
        if (idx < 0 || idx >= this.length) {
            return undefined;
        }

        if (idx === 0) {
            const value = this.head!.value;
            this.head = this.head!.next;
            this.length--;
            return value;
        }

        let currentNode = this.head;
        let prevNode: ListNode<T> | undefined;
        let currentIndex = 0;

        while (currentNode && currentIndex < idx) {
            prevNode = currentNode;
            currentNode = currentNode.next;
            currentIndex++;
        }

        if (currentNode) {
            prevNode!.next = currentNode.next;
            this.length--;
            return currentNode.value;
        }

        return undefined;
    }
}
