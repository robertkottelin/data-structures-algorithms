class ListNode<T> {
    value: T;
    next: ListNode<T> | null;

    constructor(value: T) {
        this.value = value;
        this.next = null;
    }
}

export default class LinkedList<T> {
    private head: ListNode<T> | null;
    private tail: ListNode<T> | null;
    public length: number;

    constructor() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    prepend(value: T): void {
        const newNode = new ListNode(value);
        newNode.next = this.head;
        this.head = newNode;
        if (!this.tail) {
            this.tail = newNode;
        }
        this.length++;
    }

    append(value: T): void {
        const newNode = new ListNode(value);
        if (!this.head) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            if (this.tail) {
                this.tail.next = newNode;
            }
            this.tail = newNode;
        }
        this.length++;
    }

    insertAt(value: T, index: number): void {
        if (index < 0 || index > this.length) {
            throw new Error('Index out of bounds');
        }

        if (index === 0) {
            this.prepend(value);
            return;
        }

        if (index === this.length) {
            this.append(value);
            return;
        }

        const newNode = new ListNode(value);
        let current = this.head;
        for (let i = 0; i < index - 1; i++) {
            if (current) {
                current = current.next;
            }
        }

        if (current) {
            newNode.next = current.next;
            current.next = newNode;
        }

        this.length++;
    }

    remove(value: T): T | undefined {
        if (!this.head) {
            return undefined;
        }

        let current: ListNode<T> | null = this.head;
        let previous: ListNode<T> | null = null;

        while (current) {
            if (current.value === value) {
                if (previous) {
                    previous.next = current.next;
                } else {
                    this.head = current.next;
                }

                if (this.tail === current) {
                    this.tail = previous;
                }

                this.length--;
                return value;
            }

            previous = current;
            current = current.next;
        }

        return undefined;
    }


    get(index: number): T | undefined {
        if (index < 0 || index >= this.length) {
            return undefined;
        }

        let current = this.head;
        for (let i = 0; i < index; i++) {
            if (current) {
                current = current.next;
            }
        }

        return current ? current.value : undefined;
    }

    removeAt(index: number): T | undefined {
        if (index < 0 || index >= this.length) {
            return undefined;
        }

        if (index === 0) {
            const value = this.head ? this.head.value : undefined;
            this.head = this.head ? this.head.next : null;
            if (this.length === 1) {
                this.tail = null;
            }
            this.length--;
            return value;
        }

        let current = this.head;
        let previous: ListNode<T> | null = null;
        for (let i = 0; i < index; i++) {
            previous = current;
            if (current) {
                current = current.next;
            }
        }

        if (previous && current) {
            previous.next = current.next;
            if (this.tail === current) {
                this.tail = previous;
            }
        }

        this.length--;
        return current ? current.value : undefined;
    }
}


/*
const linkedList = new LinkedList<number>();

linkedList.append(1);
linkedList.append(2);
linkedList.append(3);
linkedList.prepend(0);
linkedList.insertAt(1.5, 2);
linkedList.remove(0);
linkedList.removeAt(3);

console.log(linkedList.get(0)); // Output: 1
console.log(linkedList.get(1)); // Output: 1.5
console.log(linkedList.get(2)); // Output: 2

*/