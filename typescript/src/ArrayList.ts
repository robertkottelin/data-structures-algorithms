/**
 * A generic ArrayList class to manage a dynamic array of items.
 * The class provides methods for adding, removing, and retrieving items.
 */
export default class ArrayList<T> {
    // The number of items in the ArrayList
    public length: number;

    // The internal array to store items
    private items: T[];

    /**
     * Constructor for ArrayList.
     * Initializes an empty ArrayList with length 0.
     */
    constructor() {
        this.length = 0;
        this.items = [];
    }

    /**
     * Adds an item to the beginning of the ArrayList.
     * @param item The item to prepend.
     */
    prepend(item: T): void {
        this.items.unshift(item);
        this.length++;
    }

    /**
     * Inserts an item at a specified index in the ArrayList.
     * @param item The item to insert.
     * @param idx The index at which to insert the item.
     * @throws {Error} If the index is out of bounds.
     */
    insertAt(item: T, idx: number): void {
        if (idx < 0 || idx > this.length) {
            throw new Error('Index out of bounds');
        }

        this.items.splice(idx, 0, item);
        this.length++;
    }

    /**
     * Adds an item to the end of the ArrayList.
     * @param item The item to append.
     */
    append(item: T): void {
        this.items.push(item);
        this.length++;
    }

    /**
     * Removes the first occurrence of an item from the ArrayList.
     * @param item The item to remove.
     * @returns The removed item or undefined if not found.
     */
    remove(item: T): T | undefined {
        const idx = this.items.indexOf(item);
        if (idx === -1) {
            return undefined;
        }

        this.items.splice(idx, 1);
        this.length--;
        return item;
    }

    /**
     * Retrieves the item at a specified index in the ArrayList.
     * @param idx The index of the item to retrieve.
     * @returns The item at the specified index or undefined if index is out of bounds.
     */
    get(idx: number): T | undefined {
        if (idx < 0 || idx >= this.length) {
            return undefined;
        }

        return this.items[idx];
    }

    /**
     * Removes the item at a specified index in the ArrayList.
     * @param idx The index of the item to remove.
     * @returns The removed item or undefined if index is out of bounds.
     */
    removeAt(idx: number): T | undefined {
        if (idx < 0 || idx >= this.length) {
            return undefined;
        }

        const [item] = this.items.splice(idx, 1);
        this.length--;
        return item;
    }
}
