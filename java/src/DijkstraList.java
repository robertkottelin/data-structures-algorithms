package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DijkstraList {

    // Class representing an edge in a graph with a destination and a weight
    // represents a connection between two nodes in a graph
    public static class Edge {
        // integer representing the destination node of the edge, tells you which node this edge connects to.
        int to;
        // an integer representing the weight (or cost) of the edge. The weight is used in Dijkstra's algorithm 
        // to determine the shortest path between nodes in the graph.
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Helper function to check if there are unvisited nodes with finite distances
    private static boolean hasUnvisited(boolean[] seen, double[] dist) {
        for (int i = 0; i < seen.length; ++i) {
            if (!seen[i] && dist[i] < Double.POSITIVE_INFINITY) {
                return true;
            }
        }
        return false;
    }

    // Helper function to find the index of the unvisited node with the lowest distance
    private static int getLowestUnvisited(boolean[] seen, double[] dist) {
        int idx = -1;
        double lowestDistance = Double.POSITIVE_INFINITY;
        for (int i = 0; i < seen.length; ++i) {
            if (seen[i]) {
                continue;
            }

            if (dist[i] < lowestDistance) {
                lowestDistance = dist[i];
                idx = i;
            }
        }

        return idx;
    }

    // Dijkstra's algorithm to find the shortest path between two nodes in a graph
    // Running time: O(V^2 + E)
    /*
    int source: This is an integer representing the index of the starting node in the graph. 
    The algorithm will find the shortest path from this node to the destination node (sink).

    List<List<Edge>> arr: This is a nested list of Edge objects, representing the adjacency list of the graph. 
    An adjacency list is a data structure used to represent a graph, 
    where each node in the graph is associated with a list of its adjacent nodes connected by edges. 
    In this case, the outer list has a length equal to the number of nodes in the graph, 
    and each inner list contains Edge objects that represent the edges connected to the corresponding node.
    */
    public static List<Integer> dijkstraList(int source, int sink, List<List<Edge>> arr) {
        // Initialize seen (visited) and dist (distance) arrays
        // seen[] boolean array indicating whether a node has been visited or not
        boolean[] seen = new boolean[arr.size()];
        Arrays.fill(seen, false);
        // dists[] stores the shortest distance from the source node to each node in the graph.
        double[] dists = new double[arr.size()];
        Arrays.fill(dists, Double.POSITIVE_INFINITY);
        // prev[] stores the index of the previous node in the shortest path for each node.
        int[] prev = new int[arr.size()];
        Arrays.fill(prev, -1);

        // Set the distance of the source node to zero
        dists[source] = 0;

        // Use a while loop to continue the algorithm while there are unvisited nodes with finite distances left. 
        // This is checked using the hasUnvisited helper function.
        while (hasUnvisited(seen, dists)) {
            // Get the index of the unvisited node with the lowest distance
            int curr = getLowestUnvisited(seen, dists);
            // Mark the current node as visited
            seen[curr] = true;

            // Iterate through the current node's adjacent edges, skipping the ones that lead to visited nodes.
            List<Edge> adjs = arr.get(curr);
            for (int i = 0; i < adjs.size(); ++i) {
                Edge edge = adjs.get(i);
                // Skip if the adjacent node is already visited
                if (seen[edge.to]) {
                    continue;
                }
                // Calculate the new distance to each adjacent node
                double dist = dists[curr] + edge.weight;
                // Update the distance and previous node if the new distance is shorter
                if (dist < dists[edge.to]) {
                    dists[edge.to] = dist;
                    prev[edge.to] = curr;
                }
            }
        }

        // Construct the shortest path by traversing the prev[] array, starting from the sink node.
        List<Integer> out = new ArrayList<>();
        int curr = sink;
        while (prev[curr] != -1) {
            out.add(curr);
            curr = prev[curr];
        }

        // Reverse the path to get the correct order and return it as a list of node indices.
        out.add(source);
        List<Integer> reversedOut = new ArrayList<>(out);
        java.util.Collections.reverse(reversedOut);

        // Return the shortest path from source to sink as a list of node indices
        return reversedOut;
    }
}

