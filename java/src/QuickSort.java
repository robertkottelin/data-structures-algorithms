package src;

public class QuickSort {

    // Main method to sort an array using the QuickSort algorithm
    public static void quick_sort(int[] arr) {
        qs(arr, 0, arr.length - 1);
    }

    // Recursive method to sort the array
    private static void qs(int[] arr, int lo, int hi) {
        // Base case: return if the low index is greater than the high index
        if (lo <= hi) {
            // Partition the array and get the pivot index
            int pivotIdx = partition(arr, lo, hi);

            // Recursively sort the elements to the left of the pivot
            qs(arr, lo, pivotIdx - 1);

            // Recursively sort the elements to the right of the pivot
            qs(arr, pivotIdx + 1, hi);
        }
    }

    // Partition the array by selecting a pivot element and arranging
    // the elements around the pivot such that elements smaller than
    // the pivot come before it and elements greater than the pivot come after it
    private static int partition(int[] arr, int lo, int hi) {
        // Choose the last element as the pivot
        int pivot = arr[hi];

        // Initialize the index for elements smaller than the pivot
        int i = lo;

        // Iterate through the elements in the range [lo, hi)
        for (int j = lo; j < hi; ++j) {
            // If the current element is smaller than or equal to the pivot,
            // swap it with the element at index i and increment i
            if (arr[j] <= pivot) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }

        // Swap the pivot element with the element at index i
        arr[hi] = arr[i];
        arr[i] = pivot;

        // Return the index of the pivot element
        return i;
    }
}
