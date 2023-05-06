package src;

public class BinarySearchList {
    public static boolean bsList(int[] haystack, int needle) {
        int low = 0;
        int high = haystack.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int guess = haystack[mid];

            if (guess == needle) {
                return true;
            }

            if (guess > needle) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }
}
