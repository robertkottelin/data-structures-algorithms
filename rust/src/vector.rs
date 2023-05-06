#[derive(Debug)]
pub struct Vector<T> {
    data: Vec<T>,
    length: usize,
}

impl<T> Vector<T> {
    pub fn new() -> Self {
        Vector {
            data: Vec::new(),
            length: 0,
        }
    }

    pub fn push(&mut self, item: T) {
        self.data.push(item);
        self.length += 1;
    }

    pub fn pop(&mut self) -> Option<T> {
        if self.length == 0 {
            return None;
        }
        self.length -= 1;
        self.data.pop()
    }
}
