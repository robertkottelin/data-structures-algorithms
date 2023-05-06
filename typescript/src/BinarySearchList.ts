export default function bs_list(haystack: number[], needle: number): boolean {

    // logn
    let low = 0;
    let high = haystack.length - 1;

    while (low <= high) {
        let mid = Math.floor((low + high) / 2);
        let guess = haystack[mid];

        if (guess === needle) {
            return true;
        }

        if (guess > needle) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }

    return false;
}

// RUST
// fn bs_list(haystack: &[i32], needle: i32) -> bool {
//     let mut low = 0;
//     let mut high = haystack.len() - 1;

//     while low <= high {
//         let mid = (low + high) / 2;
//         let guess = haystack[mid];

//         if guess == needle {
//             return true;
//         }

//         if guess > needle {
//             high = mid - 1;
//         } else {
//             low = mid + 1;
//         }
//     }

//     return false;
// }