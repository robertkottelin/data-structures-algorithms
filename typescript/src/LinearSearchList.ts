export default function linear_search(haystack: number[], needle: number): boolean {
    for (let i = 0; i < haystack.length; ++i) {
        if (haystack[i] === needle) {
            return true;
        }
    }

    return false;
}

// RUST
// fn linear_search(haystack: &[i32], needle: i32) -> bool {
//     for value in haystack {
//         if value == &needle {
//             return true;
//         }
//     }

//     return false;
// }