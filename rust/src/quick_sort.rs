fn qs(arr: &mut Vec<i32>, lo: usize, hi: usize) {
    if lo < hi {
        let pivot_idx = partition(arr, lo, hi); 
        if pivot_idx > 0 {
            qs(arr, lo, pivot_idx - 1);
        }
        qs(arr, pivot_idx + 1, hi);
    }
}

// [2,4,3,5]
fn partition(arr: &mut Vec<i32>, _lo: usize, hi: usize) -> usize {
    let pivot = arr[hi];
    println!("pivot: {}", pivot);
    let mut i = _lo;
    for j in _lo..hi {
        if arr[j] < pivot {
            arr.swap(i, j);            
            i += 1;
        }
    }
    println!("before swap: {:?}", arr);
    arr.swap(hi, i);
    println!("after swap: {:?}", arr);

    i
}

pub fn quick_sort(arr: &mut Vec<i32>) {
    println!("Starting");
    if arr.is_empty() {
        return;
    }
    qs(arr, 0, arr.len() - 1);
    println!("After sort: {:?}", arr);
    println!("Ending");
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_quick_sort() {
        let mut arr = vec![5, 3, 8, 4, 2, 7, 1, 6];
        let sorted_arr = vec![1, 2, 3, 4, 5, 6, 7, 8];

        quick_sort(&mut arr);

        assert_eq!(arr, sorted_arr);
    }

    #[test]
    fn test_quick_sort_empty() {
        let mut arr: Vec<i32> = vec![];
        let sorted_arr: Vec<i32> = vec![];

        quick_sort(&mut arr);

        println!("{:?}", arr);

        assert_eq!(arr, sorted_arr);
    }

    #[test]
    fn test_quick_sort_already_sorted() {
        let mut arr = vec![1, 2, 3, 4, 5, 6, 7, 8];
        let sorted_arr = vec![1, 2, 3, 4, 5, 6, 7, 8];

        quick_sort(&mut arr);

        assert_eq!(arr, sorted_arr);
    }
}