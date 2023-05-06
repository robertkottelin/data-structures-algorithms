fn lcs(x: &str, y: &str) -> usize {
    let m = x.len();
    let n = y.len();
    let mut dp = vec![vec![0; n + 1]; m + 1];

    for i in 1..=m {
        for j in 1..=n {
            if x.chars().nth(i - 1).unwrap() == y.chars().nth(j - 1).unwrap() {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = usize::max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    dp[m][n]
}

fn main() {
    let x = "ABCBDAB";
    let y = "BDCAB";
    println!("The length of the LCS is: {}", lcs(x, y));
}
