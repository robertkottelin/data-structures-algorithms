// write a merge sort algorithm in rust that takes in a vec i32 and returns a sorted vec i32

fn merge_sort(arr: &mut Vec<i32>) {
    if arr.len() <= 1 {
        return;
    }

    let mid = arr.len() / 2;
    let mut left = arr[..mid].to_vec();
    let mut right = arr[mid..].to_vec();

    merge_sort(&mut left);
    merge_sort(&mut right);

    let mut i = 0;
    let mut j = 0;
    let mut k = 0;

    while i < left.len() && j < right.len() {
        if left[i] <= right[j] {
            arr[k] = left[i];
            i += 1;
        } else {
            arr[k] = right[j];
            j += 1;
        }
        k += 1;
    }

    while i < left.len() {
        arr[k] = left[i];
        i += 1;
        k += 1;
    }

    while j < right.len() {
        arr[k] = right[j];
        j += 1;
        k += 1;
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_merge_sort() {
        let mut arr = vec![5, 3, 8, 4, 2, 7, 1, 6];
        let sorted_arr = vec![1, 2, 3, 4, 5, 6, 7, 8];

        merge_sort(&mut arr);

        assert_eq!(arr, sorted_arr);
    }

    #[test]
    fn test_merge_sort_empty() {
        let mut arr: Vec<i32> = vec![];
        let sorted_arr: Vec<i32> = vec![];

        merge_sort(&mut arr);

        assert_eq!(arr, sorted_arr);
    }

    #[test]
    fn test_merge_sort_already_sorted() {
        let mut arr = vec![1, 2, 3, 4, 5, 6, 7, 8];
        let sorted_arr = vec![1, 2, 3, 4, 5, 6, 7, 8];

        merge_sort(&mut arr);

        assert_eq!(arr, sorted_arr);
    }
}
