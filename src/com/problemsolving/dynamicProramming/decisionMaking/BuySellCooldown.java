/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.decisionMaking;

/**
 * @author paras.chawla
 * @version $Id: BuySellCooldown.java, v 0.1 2020-04-08 15:38 paras.chawla Exp $$
 */
public class BuySellCooldown {

    public int maxProfit(int[] prices) {

        if (prices == null || prices.length == 0) {
            return 0;
        }

        /* buy[i]= Maximum profit which end with buying on day i or end
           with buying on a day before i and takes rest until the day i since then
             buy[i] = Max(Either fresh buy or Rest)
        */
        int buy[] = new int[prices.length];

        /* sell[i]= Maximum profit which end with selling on day i or end
        with selling on a day before i and takes rest until the day i since then.
            sell[i] = Max(Either sell or Rest)
        */
        int sell[] = new int[prices.length];

        buy[0] = -prices[0];
        buy[1] = buy[0];
        sell[0] = 0;
        sell[1] = 0;
        sell[2] = 0;

        for (int i = 2; i < prices.length; i++) {
            //Rest , Fresh buy(we need to sell before fresh buy)
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);

            // Rest , Fresh Sell( We need to buy at i-1 in order to sell something)
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        //System.out.println(sell[prices.length-1]);
        return 0;
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) { return 0; }

        int b0 = -prices[0], b1 = b0;
        int s0 = 0, s1 = 0, s2 = 0;

        for (int i = 1; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0;
            s2 = s1;
            s1 = s0;
        }
        return s0;
    }

    public static void main(String[] args) {
        new BuySellCooldown().maxProfit(new int[] {1, 2, 3, 0, 2});
        new BuySellCooldown().maxProfit2(new int[] {1, 2, 3, 0, 2});
    }
}