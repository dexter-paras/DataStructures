/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.decisionMaking;

/**
 * @author paras.chawla
 * @version $Id: BuySellStock2.java, v 0.1 2020-03-22 18:20 paras.chawla Exp $$ You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again)
 * We can sell adn then buy on same day
 */
public class BuySellStock2 {

    //[4,1,7,9,3]
    int maxProfitMultipleTransactions(int[] prices) {

        int profit = 0;
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // add to profit if next day price is greater than previous day price
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }

    public int maxProfit(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        new BuySellStock2().maxProfit(new int[]{7,1,2,4,6,4});
    }
}