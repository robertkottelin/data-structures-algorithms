package src;

// Input: stock market data, find the best time to buy and sell using dynamic programming
// Data structure: array of integers

class MaximumSubarraySum {

    // Function to find the maximum profit by buying and selling stocks
    public int maxProfit(int[] prices) {
        // If the input array is empty, return 0 as no profit can be made
        if (prices.length == 0) {
            return 0;
        }

        // Initialize the maximum profit to 0 and the minimum price to the first element in the array
        int maxProfit = 0;
        int minPrice = prices[0];

        // Iterate through the prices array starting from index 1
        for (int i = 1; i < prices.length; i++) {
            // If the current price is less than the minimum price found so far,
            // update the minimum price
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                // If the current price is greater than the minimum price,
                // calculate the profit for the current day (current price - min price)
                // and update the maximum profit if the current profit is greater than the current max profit
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }

        // Return the maximum profit
        return maxProfit;
    }

    public static void main(String[] args) {
        // Create a MaximumSubarraySum object
        MaximumSubarraySum solution = new MaximumSubarraySum();

        // Define an array of stock prices for multiple days
        int[] prices = {7, 1, 5, 3, 6, 4};

        // Call the maxProfit function and print the result
        System.out.println("Max profit: " + solution.maxProfit(prices)); // Output: 5
    }
}
