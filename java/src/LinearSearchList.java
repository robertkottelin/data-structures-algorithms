package src;

public class LinearSearchList {

    // Search for an int in an array using linear search
    public static boolean linearSearch(int[] haystack, int needle) {
        for (int i = 0; i < haystack.length; ++i) {
            if (haystack[i] == needle) {
                return true;
            }
        }

        return false;
    }

}
