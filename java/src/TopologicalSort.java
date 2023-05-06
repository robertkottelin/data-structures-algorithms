package src;

import java.util.*;

public class TopologicalSort {

    public static class Graph {
        private int vertices;
        private LinkedList<Integer>[] adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];

            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int v, int w) {
            adjList[v].add(w);
        }

        public void topologicalSort() {
            boolean[] visited = new boolean[vertices];
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    topologicalSortUtil(i, visited, stack);
                }
            }

            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
        }
    
        private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
            visited[v] = true;
            Integer i;
    
            for (Integer neighbor : adjList[v]) {
                if (!visited[neighbor]) {
                    topologicalSortUtil(neighbor, visited, stack);
                }
            }
    
            stack.push(v);
        }
    }
}    
