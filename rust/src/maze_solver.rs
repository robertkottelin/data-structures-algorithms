pub type Point = (i32, i32);

static DIR: [(i32, i32); 4] = [(-1, 0), (0, -1), (1, 0), (0, 1)];

fn walk(
    maze: &Vec<Vec<char>>,
    wall: char,
    curr: Point,
    end: Point,
    seen: &mut Vec<Vec<bool>>,
    path: &mut Vec<Point>,
) -> bool {
    let (x, y) = curr;

    if x < 0 || x >= maze[0].len() as i32 || y < 0 || y >= maze.len() as i32 {
        return false;
    }

    if maze[y as usize][x as usize] == wall {
        return false;
    }

    if curr == end {
        path.push(end);
        return true;
    }

    if seen[y as usize][x as usize] {
        return false;
    }

    seen[y as usize][x as usize] = true;
    path.push(curr);

    for (dx, dy) in DIR.iter() {
        let next_point = (x + dx, y + dy);
        if walk(maze, wall, next_point, end, seen, path) {
            return true;
        }
    }

    path.pop();

    false
}

pub fn solve(maze: Vec<Vec<char>>, wall: char, start: Point, end: Point) -> Vec<Point> {
    let mut seen: Vec<Vec<bool>> = Vec::new();
    let mut path: Vec<Point> = Vec::new();

    for _ in 0..maze.len() {
        seen.push(vec![false; maze[0].len()]);
    }

    walk(&maze, wall, start, end, &mut seen, &mut path);
    path
}
