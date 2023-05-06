class GraphNode<T> {
    value: T;
    neighbors: GraphNode<T>[];

    constructor(value: T) {
        this.value = value;
        this.neighbors = [];
    }

    addNeighbor(neighbor: GraphNode<T>): void {
        this.neighbors.push(neighbor);
    }
}

class Graph<T> {
    nodes: GraphNode<T>[];

    constructor() {
        this.nodes = [];
    }

    addNode(value: T): GraphNode<T> {
        const node = new GraphNode(value);
        this.nodes.push(node);
        return node;
    }
}


const graph = new Graph<number>();

const node1 = graph.addNode(1);
const node2 = graph.addNode(2);
const node3 = graph.addNode(3);

node1.addNeighbor(node2);
node1.addNeighbor(node3);
node2.addNeighbor(node3);

console.log(node1.neighbors); // Output: [GraphNode { value: 2, neighbors: [] }, GraphNode { value: 3, neighbors: [] }]
console.log(node2.neighbors); // Output: [GraphNode { value: 3, neighbors: [] }]
console.log(node3.neighbors); // Output: []
