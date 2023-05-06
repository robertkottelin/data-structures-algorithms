/**
 * Performs Breadth-First Search on a given weighted graph to find the shortest path between two nodes.
 * @param graph A weighted adjacency matrix representing the graph.
 * @param source The source node from which to start the search.
 * @param needle The target node to search for.
 * @returns An array of node indices in the shortest path from source to needle, or null if no path exists.
 */
export default function bfs(graph: WeightedAdjacencyMatrix, source: number, needle: number): number[] | null {
    // Create an array to keep track of visited nodes
    const seen = new Array(graph.length).fill(false);

    // Create an array to store the previous node in the shortest path for each node
    const prev = new Array(graph.length).fill(-1);

    // Mark the source node as visited
    seen[source] = true;

    // Initialize the queue with the source node
    const q: number[] = [source];

    // Continue searching while the queue is not empty
    do {
        // Dequeue the next node to visit
        const curr = q.shift() as number;

        // If we've found the target node, exit the loop
        if (curr === needle) {
            break;
        }

        // Retrieve the adjacent nodes of the current node
        const adjs = graph[curr];

        // Iterate over the adjacent nodes
        for (let i = 0; i < adjs.length; ++i) {
            // If the adjacent node is not connected or has already been visited, skip it
            if (adjs[i] === 0 || seen[i]) {
                continue;
            }

            // Mark the adjacent node as visited
            seen[i] = true;

            // Set the previous node in the shortest path for the adjacent node
            prev[i] = curr;

            // Enqueue the adjacent node for future exploration
            q.push(i);
        }
    } while (q.length);

    // If there's no path to the target node, return null
    if (prev[needle] === -1) {
        return null;
    }

    // Build the shortest path by iterating through the 'prev' array backwards
    let curr = needle;
    const out: number[] = [];

    while (prev[curr] !== -1) {
        out.push(curr);
        curr = prev[curr];
    }

    // If there's a path, return it in the correct order (from source to needle)
    if (out.length) {
        return [source].concat(out.reverse());
    }

    // If there's no path (source and needle are the same), return an empty array
    return [];
}
