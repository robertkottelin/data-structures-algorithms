pub type WeightedAdjacencyMatrix = Vec<Vec<usize>>;

pub fn bfs(graph: &WeightedAdjacencyMatrix, source: usize, needle: usize) -> Option<Vec<usize>> {
    let mut seen = vec![false; graph.len()];
    let mut prev = vec![usize::MAX; graph.len()];

    seen[source] = true;
    let mut q = Vec::new();
    q.push(source);

    while let Some(curr) = q.pop() {
        if curr == needle {
            break;
        }

        let adjs = &graph[curr];
        for (i, &weight) in adjs.iter().enumerate() {
            if weight == 0 || seen[i] {
                continue;
            }
            seen[i] = true;
            prev[i] = curr;
            q.push(i);
        }
    }

    if prev[needle] == usize::MAX {
        return None;
    }

    // Build the path backwards
    let mut curr = needle;
    let mut out = Vec::new();

    while prev[curr] != usize::MAX {
        out.push(curr);
        curr = prev[curr];
    }

    if !out.is_empty() {
        out.push(source);
        out.reverse();
        return Some(out);
    }

    Some(Vec::new())
}
