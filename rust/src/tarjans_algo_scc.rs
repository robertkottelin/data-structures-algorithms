use std::cmp::min;
use std::collections::{HashMap, HashSet, VecDeque};

struct Tarjan {
    graph: HashMap<usize, Vec<usize>>,
    index: usize,
    stack: VecDeque<usize>,
    indices: HashMap<usize, usize>,
    lowlinks: HashMap<usize, usize>,
    on_stack: HashSet<usize>,
    sccs: Vec<Vec<usize>>,
}

impl Tarjan {
    fn new(graph: HashMap<usize, Vec<usize>>) -> Self {
        Tarjan {
            graph,
            index: 0,
            stack: VecDeque::new(),
            indices: HashMap::new(),
            lowlinks: HashMap::new(),
            on_stack: HashSet::new(),
            sccs: Vec::new(),
        }
    }

    fn run(&mut self) -> Vec<Vec<usize>> {
        for node in self.graph.keys() {
            if !self.indices.contains_key(node) {
                self.strong_connect(*node);
            }
        }
        self.sccs.clone()
    }

    fn strong_connect(&mut self, node: usize) {
        self.indices.insert(node, self.index);
        self.lowlinks.insert(node, self.index);
        self.index += 1;
        self.stack.push_front(node);
        self.on_stack.insert(node);

        if let Some(neighbors) = self.graph.get(&node) {
            for &neighbor in neighbors {
                if !self.indices.contains_key(&neighbor) {
                    self.strong_connect(neighbor);
                    let neighbor_lowlink = self.lowlinks[&neighbor];
                    let node_lowlink = self.lowlinks[&node];
                    self.lowlinks.insert(node, min(node_lowlink, neighbor_lowlink));
                } else if self.on_stack.contains(&neighbor) {
                    let neighbor_index = self.indices[&neighbor];
                    let node_lowlink = self.lowlinks[&node];
                    self.lowlinks.insert(node, min(node_lowlink, neighbor_index));
                }
            }
        }

        if self.lowlinks[&node] == self.indices[&node] {
            let mut scc = Vec::new();
            loop {
                let w = self.stack.pop_front().unwrap();
                self.on_stack.remove(&w);
                scc.push(w);
                if w == node {
                    break;
                }
            }
            self.sccs.push(scc);
        }
    }
}

// fn main() {
//     let graph = [
//         (1, vec![2]),
//         (2, vec![3]),
//         (3, vec![1]),
//         (4, vec![2, 3, 5]),
//         (5, vec![4, 6]),
//         (6, vec![3, 7]),
//         (7, vec![6]),
//     ]
//     .iter()
//     .cloned()
//     .collect();

//     let mut tarjan = Tarjan::new(graph);
//     let sccs = tarjan.run();

//     println!("Strongly connected components: {:?}", sccs);
// }
