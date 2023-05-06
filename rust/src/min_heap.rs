pub struct MinHeap {
    length: usize,
    data: Vec<i32>,
}

impl MinHeap {
    pub fn new() -> Self {
        MinHeap {
            data: Vec::new(),
            length: 0,
        }
    }

    // Big O: logn
    pub fn insert(&mut self, value: i32) {
        self.data.push(value);
        self.heapify_up(self.length);
        self.length += 1;
    }

    // Big O: logn
    pub fn delete(&mut self) -> i32 {
        if self.length == 0 {
            panic!("Heap is empty");
        }
        let out = self.data[0];
        self.length -= 1;

        if self.length == 0 {
            self.data.clear();
            return out;
        }

        self.data[0] = self.data[self.length];
        self.heapify_down(0);

        return out;
    }

    fn heapify_down(&mut self, idx: usize) {
        let l_idx = self.left_child(idx);
        let r_idx = self.right_child(idx);

        if idx >= self.length || l_idx >= self.length {
            return;
        }

        let l_v = self.data[l_idx];
        let r_v = self.data[r_idx];
        let v = self.data[idx];

        if l_v > v && v > r_v {
            self.data[idx] = r_v;
            self.data[r_idx] = v;
            self.heapify_down(r_idx);
        } else if l_v < v && v < r_v {
            self.data[idx] = l_v;
            self.data[l_idx] = v;
            self.heapify_down(l_idx);
        }
    }

    fn heapify_up(&mut self, idx: usize) {
        if idx == 0 {
            return;
        }

        let p = self.parent(idx);
        let parent_v = self.data[p];
        let v = self.data[idx];

        if parent_v > v {
            self.data[idx] = parent_v;
            self.data[p] = v;
            self.heapify_up(p);
        }
    }

    fn parent(&self, idx: usize) -> usize {
        (idx - 1) / 2
    }

    fn left_child(&self, idx: usize) -> usize {
        2 * idx + 1
    }

    fn right_child(&self, idx: usize) -> usize {
        2 * idx + 2
    }

    fn swap(&mut self, idx1: usize, idx2: usize) {
        self.data.swap(idx1, idx2);
    }
}
