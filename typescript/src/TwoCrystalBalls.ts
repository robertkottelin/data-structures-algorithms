export default function two_crystal_balls(breaks: boolean[]): number {

    const jmpAmount = Math.floor(Math.sqrt(breaks.length));

    let i = jmpAmount;
    for (; i < breaks.length; i += jmpAmount) {
        if (breaks[i]) {
            break;
        }
    }

    i -= jmpAmount;

    for (let j = 0; j <= jmpAmount && i < breaks.length; ++j, ++i) {
        if (breaks[i]) {
            return i;
        }
    }

    return -1;

}

// same as above but not with sqrt
export function two_crystal_balls_2(breaks: boolean[]): number {
    let jmpAmount = 1;
    let i = jmpAmount;
    for (; i < breaks.length; i += jmpAmount) {
        if (breaks[i]) {
            break;
        }
        jmpAmount++;
    }

    i -= jmpAmount;

    for (let j = 0; j <= jmpAmount && i < breaks.length; ++j, ++i) {
        if (breaks[i]) {
            return i;
        }
    }

    return -1;

}


// RUST
// fn two_crystal_balls(breaks: &[bool]) -> isize {
//     let jmp_amount = (breaks.len() as f64).sqrt().floor() as isize;
//     let mut i = jmp_amount;
//     for _ in 0.. {
//         if breaks[i as usize] {
//             break;
//         }
//         i += jmp_amount;
//     }
//     i -= jmp_amount;
//     for j in 0..=jmp_amount {
//         if breaks[(i + j) as usize] {
//             return i + j;
//         }
//     }
//     -1
// }