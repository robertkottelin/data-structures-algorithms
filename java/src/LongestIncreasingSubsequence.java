package src;

// Find the length of the longest increasing subsequence in an array of integers.

public class LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int maxLength = 0;

        for (int num : nums) {
            int i = 0, j = maxLength;
            while (i != j) {
                int mid = (i + j) / 2;
                if (dp[mid] < num) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }

            dp[i] = num;
            if (i == maxLength) {
                maxLength++;
            }
        }

        return maxLength;
    }
}
