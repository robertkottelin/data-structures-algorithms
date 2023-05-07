fn max_profit(prices: &[i32]) -> i32 {
    if prices.is_empty() {
        return 0;
    }

    let mut max_profit = 0;
    let mut min_price = prices[0];

    for &price in prices.iter().skip(1) {
        if price < min_price {
            min_price = price;
        } else {
            max_profit = max_profit.max(price - min_price);
        }
    }

    max_profit
}

fn max_profit_multiple_transactions(prices: &[i32]) -> i32 {
    let mut profit = 0;

    for i in 1..prices.len() {
        if prices[i] > prices[i - 1] {
            profit += prices[i] - prices[i - 1];
        }
    }

    profit
}

// fn main() {
//     let prices = [7, 1, 5, 3, 6, 4];
//     println!("Max profit (single transaction): {}", max_profit(&prices)); // Output: 5
//     println!("Max profit (multiple transactions): {}", max_profit_multiple_transactions(&prices)); // Output: 7
// }
