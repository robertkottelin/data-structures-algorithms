fn two_crystal_balls(breaks: &[bool]) -> isize {
    let jmp_amount = (breaks.len() as f64).sqrt().floor() as isize;
    let mut i = jmp_amount;
    for _ in 0.. {
        if breaks[i as usize] {
            break;
        }
        i += jmp_amount;
    }
    i -= jmp_amount;
    for j in 0..=jmp_amount {
        if breaks[(i + j) as usize] {
            return i + j;
        }
    }
    -1
}