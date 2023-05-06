package src;

public class MinHeap {
    public int length;
    private int[] data;

    public MinHeap() {
        this.data = new int[0];
        this.length = 0;
    }

    public void insert(int value) {
        int[] newData = new int[this.length + 1];
        System.arraycopy(this.data, 0, newData, 0, this.length);
        this.data = newData;
        this.data[this.length] = value;
        this.heapifyUp(this.length);
        this.length++;
    }

    public int delete() {
        if (this.length == 0) {
            throw new RuntimeException("Heap is empty");
        }
        int out = this.data[0];
        this.length--;

        if (this.length == 0) {
            this.data = new int[0];
            return out;
        }

        this.data[0] = this.data[this.length];
        int[] newData = new int[this.length];
        System.arraycopy(this.data, 0, newData, 0, this.length);
        this.data = newData;
        this.heapifyDown(0);

        return out;
    }

    private void heapifyDown(int idx) {
        int lIdx = this.leftChild(idx);
        int rIdx = this.rightChild(idx);

        if (idx >= this.length || lIdx >= this.length) {
            return;
        }

        int lV = this.data[lIdx];
        int rV = this.data[rIdx];
        int v = this.data[idx];

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

    private void heapifyUp(int idx) {
        if (idx == 0) {
            return;
        }

        int p = this.parent(idx);
        int parentV = this.data[p];
        int v = this.data[idx];

        if (parentV > v) {
            this.data[idx] = parentV;
            this.data[p] = v;
            this.heapifyUp(p);
        }
    }

    private int parent(int idx) {
        return (idx - 1) / 2;
    }

    private int leftChild(int idx) {
        return 2 * idx + 1;
    }

    private int rightChild(int idx) {
        return 2 * idx + 2;
    }

    // private void swap(int idx1, int idx2) {
    //     int temp = this.data[idx1];
    //     this.data[idx1] = this.data[idx2];
    //     this.data[idx2] = temp;
    // }
}
