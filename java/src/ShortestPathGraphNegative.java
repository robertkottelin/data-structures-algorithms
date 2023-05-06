package src;

// Find the shortest path in a weighted graph with negative weights (no negative weight cycles). Use the Bellman-Ford algorithm.
// Data structure: Graph with adjacency list representation.

import java.util.*;

class Edge {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    int vertices;
    List<Edge> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);
    }

    public void bellmanFord(int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : edges) {
                int src = edge.source;
                int dest = edge.destination;
                int weight = edge.weight;

                if (distance[src] != Integer.MAX_VALUE && distance[src] + weight < distance[dest]) {
                    distance[dest] = distance[src] + weight;
                }
            }
        }

        for (Edge edge : edges) {
            int src = edge.source;
            int dest = edge.destination;
            int weight = edge.weight;

            if (distance[src] != Integer.MAX_VALUE && distance[src] + weight < distance[dest]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        printSolution(distance);
    }

    public void printSolution(int[] distance) {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + "\t\t" + distance[i]);
        }
    }
}
