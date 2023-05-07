fn coin_change(coins: &[usize], amount: usize) -> i32 {
    let max_value = amount + 1;
    let mut dp = vec![max_value; amount + 1];
    dp[0] = 0;

    for i in 1..=amount {
        for &coin in coins {
            if i >= coin {
                dp[i] = usize::min(dp[i], dp[i - coin] + 1);
            }
        }
    }

    if dp[amount] != max_value {
        dp[amount] as i32
    } else {
        -1
    }
}

// fn main() {
//     let coins = [1, 2, 5];
//     let amount = 11;
//     println!("Fewest number of coins that sum up to {}: {}", amount, coin_change(&coins, amount));
// }
