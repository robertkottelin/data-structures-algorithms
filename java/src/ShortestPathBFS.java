package src;

// Find the shortest path between two nodes in a graph using BFS.
// Data structure: BFSGraphMatrix.java

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBFS {
    public int shortestPath(int[][] graph, int start, int end) {
        int vertices = graph.length;
        boolean[] visited = new boolean[vertices];
        int[] distance = new int[vertices];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == end) {
                return distance[current];
            }

            for (int i = 0; i < vertices; i++) {
                if (graph[current][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    distance[i] = distance[current] + 1;
                }
            }
        }

        return -1;
    }
}
