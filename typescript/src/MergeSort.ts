function merge_sort(arr: any[]) {
    if (arr.length <= 1) {
        return;
    }

    let mid = arr.length / 2;
    let left = arr.slice(0, mid);
    let right = arr.slice(mid);

    merge_sort(left);
    merge_sort(right);

    let i = 0;
    let j = 0;
    let k = 0;

    while (i < left.length && j < right.length) {
        if (left[i] <= right[j]) {
            arr[k] = left[i];
            i += 1;
        } else {
            arr[k] = right[j];
            j += 1;
        }
        k += 1;
    }

    while (i < left.length) {
        arr[k] = left[i];
        i += 1;
        k += 1;
    }

    while (j < right.length) {
        arr[k] = right[j];
        j += 1;
        k += 1;
    }
}