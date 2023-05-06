fn longest_increasing_subarray(arr: &[i32]) -> &[i32] {
    let mut start = 0;
    let mut end = 0;
    let mut max_start = 0;
    let mut max_length = 0;

    for i in 1..arr.len() {
        if arr[i] > arr[i - 1] {
            end = i;
            if end - start + 1 > max_length {
                max_start = start;
                max_length = end - start + 1;
            }
        } else {
            start = i;
        }
    }

    &arr[max_start..max_start + max_length]
}
