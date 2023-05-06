use std::collections::HashMap;

type WeightedAdjacencyList = HashMap<usize, Vec<(usize, f64)>>;

fn walk(
    graph: &WeightedAdjacencyList,
    curr: usize,
    needle: usize,
    seen: &mut Vec<bool>,
    path: &mut Vec<usize>,
) -> bool {
    // base case 1
    if curr == needle {
        return true;
    }

    // base case 2
    if seen[curr] {
        return false;
    }

    seen[curr] = true;

    // recurse
    // pre
    path.push(curr);
    if curr == needle {
        return true;
    }

    // recurse
    if let Some(list) = graph.get(&curr) {
        for &(to, _) in list {
            if walk(graph, to, needle, seen, path) {
                return true;
            }
        }
    }

    // post
    path.pop();

    false
}

// running time: O(V + E)
pub fn dfs(
    graph: &WeightedAdjacencyList,
    source: usize,
    needle: usize,
) -> Option<Vec<usize>> {
    let mut seen = vec![false; graph.len()];
    let mut path = Vec::new();

    if walk(graph, source, needle, &mut seen, &mut path) {
        Some(path)
    } else {
        None
    }
}
