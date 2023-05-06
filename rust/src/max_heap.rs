struct MaxHeap {
    data: Vec<i32>,
}

impl MaxHeap {
    fn new() -> Self {
        MaxHeap { data: vec![] }
    }

    fn parent_index(index: usize) -> usize {
        (index - 1) / 2
    }

    fn left_child_index(index: usize) -> usize {
        2 * index + 1
    }

    fn right_child_index(index: usize) -> usize {
        2 * index + 2
    }

    fn push(&mut self, value: i32) {
        self.data.push(value);
        let mut index = self.data.len() - 1;

        while index > 0 && self.data[Self::parent_index(index)] < self.data[index] {
            self.data.swap(index, Self::parent_index(index));
            index = Self::parent_index(index);
        }
    }

    fn pop_max(&mut self) -> Option<i32> {
        if self.data.is_empty() {
            return None;
        }

        let max_value = self.data[0];
        let last_value = self.data.pop().unwrap();

        if !self.data.is_empty() {
            self.data[0] = last_value;
            let mut index = 0;
            let length = self.data.len();

            loop {
                let left_child_index = Self::left_child_index(index);
                let right_child_index = Self::right_child_index(index);
                let mut largest_index = index;

                if left_child_index < length
                    && self.data[left_child_index] > self.data[largest_index]
                {
                    largest_index = left_child_index;
                }

                if right_child_index < length
                    && self.data[right_child_index] > self.data[largest_index]
                {
                    largest_index = right_child_index;
                }

                if largest_index == index {
                    break;
                }

                self.data.swap(index, largest_index);
                index = largest_index;
            }
        }

        Some(max_value)
    }
}
