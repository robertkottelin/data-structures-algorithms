pub type WeightedEdge = (usize, i32); // (to, weight)
pub type WeightedAdjacencyList = Vec<Vec<WeightedEdge>>;

fn has_unvisited(seen: &[bool], dist: &[f64]) -> bool {
    seen.iter().zip(dist).any(|(s, d)| !s && *d < f64::INFINITY)
}

fn get_lowest_unvisited(seen: &[bool], dist: &[f64]) -> usize {
    let mut idx = None;
    let mut lowest_distance = f64::INFINITY;

    for (i, (s, d)) in seen.iter().zip(dist).enumerate() {
        if *s {
            continue;
        }

        if *d < lowest_distance {
            lowest_distance = *d;
            idx = Some(i);
        }
    }

    idx.unwrap_or(0)
}

pub fn djikstra_list(source: usize, sink: usize, arr: &WeightedAdjacencyList) -> Vec<usize> {
    let mut seen = vec![false; arr.len()];
    let mut dists = vec![f64::INFINITY; arr.len()];
    let mut prev = vec![None; arr.len()];

    dists[source] = 0.0;

    while has_unvisited(&seen, &dists) {
        let curr = get_lowest_unvisited(&seen, &dists);
        seen[curr] = true;

        let adjs = &arr[curr];

        for &(to, weight) in adjs {
            if seen[to] {
                continue;
            }
            let dist = dists[curr] + weight as f64;
            if dist < dists[to] {
                dists[to] = dist;
                prev[to] = Some(curr);
            }
        }
    }

    let mut out = Vec::new();
    let mut curr = sink;

    while let Some(p) = prev[curr] {
        out.push(curr);
        curr = p;
    }

    out.push(source);
    out.reverse();
    out
}
