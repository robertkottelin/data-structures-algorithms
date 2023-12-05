using System;
static class QuickSort {
    static void Qs(int[] arr, int lo, int hi) {
        if (lo <= hi) {
            var pivotIdx = Partition(arr, lo, hi);
            Qs(arr, lo, pivotIdx - 1);
            Qs(arr, pivotIdx + 1, hi);
        }
    }

    static int Partition(int[] arr, int _lo, int hi) {
        var pivot = arr[hi];
        var i = _lo;
        for (var j = _lo; j < hi; ++j) {
            if (arr[j] <= pivot) {
                var tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }

        arr[hi] = arr[i];
        arr[i] = pivot;

        return i;
    }

    public static int[] Sort(int[] arr) {
        Qs(arr, 0, arr.Length - 1);
        return arr;
    }
}