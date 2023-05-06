package src;

import java.util.*;

public class BFSGraphMatrix {
    public static List<Integer> bfs(int[][] graph, int source, int needle) {
        boolean[] seen = new boolean[graph.length];
        int[] prev = new int[graph.length];
        Arrays.fill(prev, -1);

        seen[source] = true;
        LinkedList<Integer> q = new LinkedList<Integer>();

        q.add(source);

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr == needle) {
                break;
            }

            int[] adjs = graph[curr];
            for (int i = 0; i < adjs.length; ++i) {
                if (adjs[i] == 0 || seen[i]) {
                    continue;
                }
                seen[i] = true;
                prev[i] = curr;
                q.add(i);
            }
        }

        if (prev[needle] == -1) {
            return null;
        }

        // build the path backwards
        List<Integer> out = new ArrayList<Integer>();
        int curr = needle;

        while (prev[curr] != -1) {
            out.add(curr);
            curr = prev[curr];
        }

        if (!out.isEmpty()) {
            Collections.reverse(out);
            out.add(0, source);
            return out;
        }

        return new ArrayList<>();
    }
}