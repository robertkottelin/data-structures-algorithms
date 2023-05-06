// Helper function to check if there are unvisited nodes with finite distances.
function hasUnvisited(seen: boolean[], dist: number[]): boolean {
    // Returns true if there is at least one unvisited node with a finite distance.
    return seen.some((s, i) => !s && dist[i] < Infinity);
}

// Helper function to find the index of the unvisited node with the lowest distance.
function getLowestUnvisited(seen: boolean[], dist: number[]): number {
    let idx = -1;
    let lowestDistance = Infinity;

    // Iterate through all nodes.
    for (let i = 0; i < seen.length; ++i) {
        // If the node is visited, skip it.
        if (seen[i]) {
            continue;
        }

        // Update the lowest distance and index if a lower distance is found.
        if (dist[i] < lowestDistance) {
            lowestDistance = dist[i];
            idx = i;
        }
    }

    return idx;
}

// Dijkstra's algorithm implementation to find the shortest path between two nodes in a graph.
// Running time: O(V^2 + E)
export default function djikstra_list(
    source: number, // The index of the starting node in the graph.
    sink: number, // The index of the destination node in the graph.
    arr: WeightedAdjacencyList // The adjacency list representing the graph.
): number[] {
    // Initialize seen (visited) and dist (distance) arrays.
    const seen = new Array(arr.length).fill(false);
    const dists = new Array(arr.length).fill(Infinity); // represents the shortest distance from the source node to every other node in the graph
    const prev = new Array(arr.length).fill(-1); // Initialize previous node array.

    dists[source] = 0; // Set the distance of the source node to zero.

    // Continue while there are unvisited nodes with finite distances.
    while (hasUnvisited(seen, dists)) {
        // Get the index of the unvisited node with the lowest distance.
        const curr = getLowestUnvisited(seen, dists);
        seen[curr] = true; // Mark the current node as visited.

        const adjs = arr[curr]; // Get the adjacent edges of the current node.

        // Iterate through the current node's adjacent edges.
        for (let i = 0; i < adjs.length; ++i) {
            const edge = adjs[i];
            // Skip if the adjacent node is already visited.
            if (seen[edge.to]) {
                continue;
            }
            // Calculate the new distance to the adjacent node.
            const dist = dists[curr] + edge.weight;
            // Update the distance and previous node if the new distance is shorter.
            if (dist < dists[edge.to]) {
                dists[edge.to] = dist;
                prev[edge.to] = curr;
            }
        }
    }

    const out: number[] = []; // Initialize an array to store the shortest path.
    let curr = sink; // Start at the destination node.

    // Construct the shortest path by traversing the previous nodes array.
    while (prev[curr] !== -1) {
        out.push(curr);
        curr = prev[curr];
    }

    // Add the source node and reverse the path to get the correct order.
    out.push(source);
    return out.reverse();
}
