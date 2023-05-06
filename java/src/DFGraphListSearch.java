package src;

import java.util.ArrayList;
import java.util.List;

public class DFGraphListSearch {

    public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static boolean walk(List<List<Edge>> graph, int curr, int needle, boolean[] seen, List<Integer> path) {
        // Base case 1
        if (curr == needle) {
            return true;
        }

        // Base case 2
        if (seen[curr]) {
            return false;
        }

        seen[curr] = true;

        // Recurse
        // Pre
        path.add(curr);
        if (curr == needle) {
            return true;
        }

        // Recurse
        List<Edge> list = graph.get(curr);
        for (int i = 0; i < list.size(); ++i) {
            Edge edge = list.get(i);
            if (walk(graph, edge.to, needle, seen, path)) {
                return true;
            }
        }

        // Post
        path.remove(path.size() - 1);

        return false;
    }

    // Running time: O(V + E)
    public static List<Integer> dfs(List<List<Edge>> graph, int source, int needle) {
        boolean[] seen = new boolean[graph.size()];
        List<Integer> path = new ArrayList<>();

        if (walk(graph, source, needle, seen, path)) {
            return path;
        }

        return path;
    }
}
