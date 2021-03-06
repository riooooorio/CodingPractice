package com.company;

public class BuyStockIV {
    public static void main(String[] args) {
        BuyStockIV s = new BuyStockIV();
        int[] array = new int[]{2, 3, 2, 1, 4, 5, 2, 11};
        System.out.println(s.maxProfit(array, 3));  // 14
        System.out.println(s.maxProfitI(array, 3)); // 14
    }

    // M[i][j]: i is total number of transactions, j is day
    // Base case:
    // M[0][0] = 0, M[i][0] = 0 (0 days), M[0][j] = 0 (0 transactions)
    // Induction rule:
    // case 1: not transacting on jth day
    //         M[i][j-1]
    // case 2: complete transaction on jth day -> sell on the jth day
    //         buy on mth day, 0 <= m < j
    //         price[j] - price[m] + M[i - 1][m]
    // M[i][j] = max(case 1, case 2)
    // Time O(m^2*n)
    // Space O(mn)
    public int maxProfit(int[] array, int k) {
        int[][] M = new int[k + 1][array.length];
        for (int i = 1; i < M.length; i++) {
            for (int j = 1; j < M[0].length; j++) {
                M[i][j] = M[i][j - 1];
                for (int m = 0; m < j; m++) {
                    M[i][j] = Math.max(M[i][j], array[j] - array[m] + M[i - 1][m]);
                }
            }
        }
        return M[k][array.length - 1];
    }

    // Base case:
    // M[0][0] = 0, M[i][0] = 0 (0 days), M[0][j] = 0 (0 transactions)
    // Induction rule:
    // case 1: not transacting on jth day
    //         M[i][j-1]
    // case 2: price[j] + maxDiff
    //         maxDiff = Math.max(maxDiff, M[i-1][j] - price[j])
    // M[i][j] = max(case 1, case 2)
    // Time O(mn)
    // Space O(mn)
    public int maxProfitI(int[] array, int k) {
        int[][] M = new int[k + 1][array.length];
        for (int i = 1; i < M.length; i++) {
            int maxDiff = -array[0];
            for (int j = 1; j < M[0].length; j++) {
                    M[i][j] = Math.max(M[i][j - 1], array[j] + maxDiff);
                    maxDiff = Math.max(maxDiff, M[i - 1][j] - array[j]);
            }
        }
        return M[k][array.length - 1];
    }

    public int maxProfitII(int[] prices, int k) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax = -prices[0];
            for (int j = 0; j < len; j++) {
                if (j == 0) {
                    t[i][j] = 0;
                    continue;
                }
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax = Math.max(tmpMax, t[i - 1][j] - prices[j]);
            }
        }
        return t[k][len - 1];
    }

    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        return profit;
    }
}
