// A class representing a Min Heap data structure.
export default class MinHeap {
    // The number of elements in the Min Heap.
    public length: number;

    // An array storing the Min Heap's elements.
    private data: number[];

    // Constructor initializing an empty Min Heap.
    constructor() {
        this.data = [];
        this.length = 0;
    }

    // Insert a value into the Min Heap.
    // Time complexity: O(log n)
    insert(value: number): void {
        // Add the value to the end of the data array.
        this.data[this.length] = value;

        // Restore the Min Heap property by moving the value to its proper position.
        this.heapifyUp(this.length);

        // Increment the length of the Min Heap.
        this.length++;
    }

    // Remove and return the minimum value from the Min Heap.
    // Time complexity: O(log n)
    delete(): number {
        // If the heap is empty, throw an error.
        if (this.length === 0) {
            throw new Error('Heap is empty');
        }

        // Save the minimum value (the root) to return later.
        const out = this.data[0];

        // Decrement the length of the Min Heap.
        this.length--;

        // If the heap is now empty, clear the data array and return the minimum value.
        if (this.length === 0) {
            this.data = [];
            return out;
        }

        // Move the last element to the root and restore the Min Heap property.
        this.data[0] = this.data[this.length];
        this.heapifyDown(0);

        // Return the minimum value.
        return out;
    }

    // Restore the Min Heap property by moving an element down the heap.
    private heapifyDown(idx: number): void {
        const lIdx = this.leftChild(idx);
        const rIdx = this.rightChild(idx);

        // If the current index or its left child are out of bounds, return.
        if (idx >= this.length || lIdx >= this.length) {
            return;
        }

        const lV = this.data[lIdx];
        const rV = this.data[rIdx];
        const v = this.data[idx];

        // Swap the current element with its smaller child, then continue down the heap.
        if (lV > v && v > rV) {
            this.data[idx] = rV;
            this.data[rIdx] = v;
            this.heapifyDown(rIdx);
        } else if (lV < v && v < rV) {
            this.data[idx] = lV;
            this.data[lIdx] = v;
            this.heapifyDown(lIdx);
        }
    }

    // Restore the Min Heap property by moving an element up the heap.
    private heapifyUp(idx: number): void {
        // If we are at the root, return.
        if (idx === 0) {
            return;
        }

        // Find the parent index and its value.
        const p = this.parent(idx);
        const parentV = this.data[p];
        const v = this.data[idx];

        // If the parent value is greater than the current value, swap them and continue up the heap.
        if (parentV > v) {
            this.data[idx] = parentV;
            this.data[p] = v;
            this.heapifyUp(p);
        }
    }

    // Helper function to find the parent index of a given index.
    private parent(idx: number): number {
        return Math.floor((idx - 1) / 2);
    }

    // Helper function to find the left child index of a given index
    private leftChild(idx: number): number {
        return 2 * idx + 1;
    }

    // Helper function to find the right child index of a given index
    private rightChild(idx: number): number {
        return 2 * idx + 2;
    }

    // Helper function to swap two values 
    private swap(idx1: number, idx2: number): void {
        const temp = this.data[idx1];
        this.data[idx1] = this.data[idx2];
        this.data[idx2] = temp;
    }
}