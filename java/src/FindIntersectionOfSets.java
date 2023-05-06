// Find the intersection of two sets.
// Data structure: HashSet

/*

This code snippet implements a method to find the intersection of two sets using the HashSet data structure in Java. 
The method intersection takes two HashSet objects, set1 and set2, as input, 
and returns a new HashSet containing the elements that are present in both input sets. 
The method iterates through each element in set1 and checks if it is present in set2 using the contains method. 
If the element is present in both sets, it is added to the resultSet. 
Finally, the resultSet is returned, containing the intersection of the two input sets.

*/

package src;

import java.util.HashSet;

public class FindIntersectionOfSets {
    // This method returns a new HashSet that contains the intersection of set1 and set2
    public HashSet<Integer> intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        // Create a new HashSet to store the result of the intersection
        HashSet<Integer> resultSet = new HashSet<>();

        // Iterate through each element in set1
        for (Integer element : set1) {
            // If set2 contains the current element from set1, add it to the resultSet
            if (set2.contains(element)) {
                resultSet.add(element);
            }
        }

        // Return the resultSet, which contains the intersection of set1 and set2
        return resultSet;
    }
}
