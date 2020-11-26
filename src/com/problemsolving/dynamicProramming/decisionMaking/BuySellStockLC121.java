/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.decisionMaking;

/**
 * @author paras.chawla
 * @version $Id: BuySellStockLC121.java, v 0.1 2019-09-26 07:10 paras.chawla Exp $$
 */
public class BuySellStockLC121 {

    // prices[] ={7, 1, 5, 3, 0, 6}
    public int maxProfitSol2(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    public int maxProfit(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        int maxProfit = 0;
        int sE = arr[0]; //7
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < sE) {
                sE = arr[i]; // sE=0
            } else {
                maxProfit = arr[i] - sE > maxProfit ? arr[i] - sE : maxProfit; //4
            }
        }
        return maxProfit;
    }

    // BEST profit selling at any given day i
    // optimal solution must belong to one of these days
    public int maxProfitDP(int[] prices) {

        // if no stock, then profit is 0
        if(prices.length==0){
            return 0;
        }

        // array containing profit if stock sell at day i
        int profit[] = new int[prices.length];
        int maxProfit=0;

        for(int i=1;i<prices.length;i++){
            // change in stock price from last day
            int priceDelta = prices[i]-prices[i-1];
            // profit at day i= change in price summation profit at day i-1
            profit[i]=Math.max(0,priceDelta+profit[i-1]);
            maxProfit=Math.max(maxProfit,profit[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int arr[] = new int[] {7, 1, 5, 3, 0, 6};
        //System.out.println(new BuySellStockLC121().maxProfit(arr));
        //System.out.println(new BuySellStockLC121().maxProfitSol2(arr));
        System.out.println(new BuySellStockLC121().maxProfitDP(arr));
    }
}