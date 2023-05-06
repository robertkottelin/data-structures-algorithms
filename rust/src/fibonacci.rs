fn fibonacci(n: u32) -> u64 {
    let mut dp = vec![0, 1];
    for i in 2..=n as usize {
        let next = dp[i - 1] + dp[i - 2];
        dp.push(next);
    }
    dp[n as usize]
}

// fn main() {
//     let n = 10;
//     println!("The {}-th Fibonacci number is: {}", n, fibonacci(n));
// }
