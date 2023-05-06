package src;

// Implement a max binary heap data structure.
// Data structure: MaxHeap (sorted BT structure)

public class MaxHeap {
    private int[] heap;
    private int size;
    private int maxSize;

    // Constructor to initialize the max binary heap
    public MaxHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        heap = new int[maxSize + 1];
        heap[0] = Integer.MAX_VALUE; // Set the first element to the maximum integer value
    }

    // Function to return the index of the parent of the given position
    private int parent(int pos) {
        return pos / 2;
    }

    // Function to return the index of the left child of the given position
    private int leftChild(int pos) {
        return 2 * pos;
    }

    // Function to return the index of the right child of the given position
    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    // Function to check if the node at the given position is a leaf node
    private boolean isLeaf(int pos) {
        return pos >= size / 2 && pos <= size;
    }

    // Function to swap the elements at the given positions in the heap array
    private void swap(int pos1, int pos2) {
        int tmp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tmp;
    }

    // Function to insert a new value into the max binary heap
    public void insert(int value) {
        // If the heap is full, do not insert the new value
        if (size >= maxSize) {
            return;
        }

        // Increment the size and insert the value at the end of the heap array
        heap[++size] = value;
        int current = size;

        // Move the inserted value up the heap until it is greater than its parent
        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }
    
    // Function to maintain the max heap property for the node at the given position
    public void maxHeapify(int pos) {
        // If the node is not a leaf node and violates the max heap property, fix the violation
        if (!isLeaf(pos)) {
            if (heap[pos] < heap[leftChild(pos)] || heap[pos] < heap[rightChild(pos)]) {
                // Swap the node with its larger child and continue heapifying the affected subtree
                if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }
    
    // Function to remove and return the maximum element (root) from the max binary heap
    public int remove() {
        // Store the maximum element and replace the root with the last element in the heap array
        int popped = heap[1];
        heap[1] = heap[size--];

        // Restore the max heap property by heapifying the root
        maxHeapify(1);
        
        // Return the maximum element
        return popped;
    }
}


/* EXAMPLE

{50, 30, 40, 20, 25, 35, 10, 18, 15}

    50
  /    \
30      40
/  \    /  \
20   25  35   10
/  \
18    15

The array representation of a heap has a unique property that enables it to be treated as a tree. 
The root node of the tree is always the first element of the array, i.e., heap[0]. 
The left child of a node at index i in the array is at index 2i+1, and the right child is at index 2i+2. 
Conversely, the parent of a node at index i is at index (i-1)/2.

*/