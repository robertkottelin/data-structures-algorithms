/*
Sort a given list of integers using the insertion sort algorithm.

This code snippet implements the insertion sort algorithm to sort a given list of integers. 
The insertionSort method takes an integer array arr as input and sorts it in ascending order. 
The algorithm iterates through the array, starting from the second element (index 1). 
In each iteration, it compares the current element (key) with the elements in the sorted part of the array (to its left). 
If the current element is smaller than the compared element, the compared element is moved one position to the right. 
This process continues until the correct position for the current element (key) is found, and it is placed in that position. 
The algorithm continues until all elements have been sorted.
*/

package src;

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        // Iterate through the array, starting from the second element (index 1)
        for (int i = 1; i < arr.length; i++) {
            // Store the current element (key) and its index (i)
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1] that are greater than the key
            // one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Place the key in its correct position within the sorted part of the array
            arr[j + 1] = key;
        }
    }
}
