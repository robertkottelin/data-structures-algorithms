package src;

public class BubbleSort {
    public void bubble_sort(int[] arr) {
        // Loop through the entire array.
        for (int i = 0; i < arr.length; ++i) {
            // Loop through the entire array again, but stop one element short.
            for (int j = 0; j < arr.length - 1; ++j) {
                // If the current element is greater than the next element, swap them.
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}

// // BAD
// public void srt(int[] a) {
//     for (int i = 0; i < a.length; ++i) {
//         for (int j = 0; j < a.length - 1; ++j) {
//             if (a[j] > a[j + 1]) {
//                 int t = a[j];
//                 a[j] = a[j + 1];
//                 a[j + 1] = t;
//             }
//         }
//         int x = 0;
//         for (int y : a) {
//             x += y;
//         }
//     }
// }



// //GOOD 
// /**
//  * Sorts an array of integers using either the bubble sort or quicksort algorithm,
//  * depending on the length of the array.
//  * @param arr The array to be sorted.
//  * @return The sorted array.
//  */
// public int[] sort(int[] arr) {
//     if (arr.length < QUICKSORT_THRESHOLD) {
//         bubbleSort(arr);
//     } else {
//         quickSort(arr, 0, arr.length - 1);
//     }
//     return arr;
// }

// /**
//  * Sorts an array of integers using the bubble sort algorithm.
//  * @param arr The array to be sorted.
//  */
// public void bubbleSort(int[] arr) {
//     for (int i = 0; i < arr.length; ++i) {
//         // Loop through the array, comparing each element with the next one.
//         for (int j = 0; j < arr.length - 1; ++j) {
//             // If the current element is greater than the next one, swap them.
//             if (arr[j] > arr[j + 1]) {
//                 int temp = arr[j];
//                 arr[j] = arr[j + 1];
//                 arr[j + 1] = temp;
//             }
//         }
//     }
// }

// /**
//  * Sorts a portion of an array of integers using the quicksort algorithm.
//  * @param arr The array to be sorted.
//  * @param left The left index of the portion to be sorted.
//  * @param right The right index of the portion to be sorted.
//  */
// private void quickSort(int[] arr, int left, int right) {
//     // Implementation of quicksort omitted for brevity.
// }
