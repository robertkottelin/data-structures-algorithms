fn insertion_sort(arr: &mut [i32]) {
    // Iterate through the array, starting from the second element (index 1)
    for i in 1..arr.len() {
        // Store the current element (key) and its index (i)
        let key = arr[i];
        let mut j = i - 1;

        // Move elements of arr[0..i-1] that are greater than the key
        // one position ahead of their current position
        while j >= 0 && arr[j] > key {
            arr[j + 1] = arr[j];
            j -= 1;
        }

        // Place the key in its correct position within the sorted part of the array
        arr[j + 1] = key;
    }
}