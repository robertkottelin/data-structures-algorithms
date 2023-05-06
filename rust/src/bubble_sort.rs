// if xi <= xi+1 == true, the array is sorted

pub fn bubble_sort(arr: &mut Vec<i32>) {
    // Loop through the entire array.
    for i in 0..arr.len() {
        // Loop through the entire array again, but stop one element short.
        for j in 0..arr.len() - 1 {
            // If the current element is greater than the next element, swap them.
            if arr[j] > arr[j + 1] {
                let temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_bubble_sort() {
        let mut arr = vec![5, 3, 8, 4, 2, 7, 1, 6];
        let sorted_arr = vec![1, 2, 3, 4, 5, 6, 7, 8];

        bubble_sort(&mut arr);

        assert_eq!(arr, sorted_arr);
    }

    #[test]
    fn test_bubble_sort_empty() {
        let mut arr: Vec<i32> = vec![];
        let sorted_arr: Vec<i32> = vec![];

        bubble_sort(&mut arr);

        assert_eq!(arr, sorted_arr);
    }

    #[test]
    fn test_bubble_sort_already_sorted() {
        let mut arr = vec![1, 2, 3, 4, 5, 6, 7, 8];
        let sorted_arr = vec![1, 2, 3, 4, 5, 6, 7, 8];

        bubble_sort(&mut arr);

        assert_eq!(arr, sorted_arr);
    }
}
