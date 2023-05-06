function qs(arr: number[], lo: number, hi: number): void {
    if (lo <= hi) {
        const pivotIdx = partition(arr, lo, hi);
        qs(arr, lo, pivotIdx - 1);
        qs(arr, pivotIdx + 1, hi);
    }
}

function partition(arr: number[], _lo: number, hi: number): number {
    const pivot = arr[hi];
    let i = _lo;
    for (let j = _lo; j < hi; ++j) {
        if (arr[j] <= pivot) {
            const tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
        }
    }

    arr[hi] = arr[i];
    arr[i] = pivot;

    return i;
}

export default function quick_sort(arr: number[]): void {
    qs(arr, 0, arr.length - 1);
}
