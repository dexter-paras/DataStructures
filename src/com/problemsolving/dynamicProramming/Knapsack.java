/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming;

/**
 * @author paras.chawla
 * @version $Id: Knapsack.java, v 0.1 2020-09-08 22:47 paras.chawla Exp $$
 */
public class Knapsack {

    public static void main(String[] args) {
        Knapsack obj = new Knapsack();
        int item[] = new int[] {60, 50, 70, 30};
        int weights[] = new int[] {5, 3, 4, 2};
        int maxWeight = 5;
        int[][] cache = new int[item.length + 1][maxWeight + 1];

        //System.out.println(obj.maxProfit(item, weights, maxWeight, item.length - 1));
        System.out.println(obj.maxProfitMemo(item, weights, maxWeight, item.length, cache));
    }

    // Approach 1- TOP DOWN Recursive
    public int maxProfit(int[] item, int[] weights, int maxWeight, int idx) {

        // 1. Base condition
        // 1.1 if knapsack weight is empty or items are null, then maxProfit is 0
        if (maxWeight == 0 || idx < 0) {
            return 0;
        }

        // 2. check whether item can be included in knapsack or not
        if (weights[idx] <= maxWeight) {

            // if item included
            int ifIncluded = item[idx] + maxProfit(item, weights, maxWeight - weights[idx], idx - 1);

            // if item not included
            int ifNotIncluded = maxProfit(item, weights, maxWeight, idx - 1);

            // 2.1 return max of if item included in knapsack or not included in knapsack
            return Math.max(ifIncluded, ifNotIncluded);
        } else {
            return maxProfit(item, weights, maxWeight, idx - 1);
        }
    }

    // Approach -2 TOP DOWN Recursive approach + Memoization approach
    public int maxProfitMemo(int[] items, int[] weights, int maxWeightConstraint, int totalItems, int[][] cache) {

        // 1. Base condition
        // 1.1 if knapsack weight is empty or items are null, then maxProfit is 0
        if (maxWeightConstraint == 0 || totalItems == 0) {
            return 0;
        }

        // 2. check whether items can be included in knapsack or not
        int idx = totalItems - 1;
        if (weights[idx] <= maxWeightConstraint) {

            // 2.1 return max of if items included in knapsack or not included in knapsack

            int withItem = items[idx] + maxProfitMemo(items, weights, maxWeightConstraint - weights[idx], totalItems - 1,
                    cache);
            int withoutItem = maxProfitMemo(items, weights, maxWeightConstraint, totalItems - 1, cache);
            cache[totalItems][maxWeightConstraint] = Math.max(withItem, withoutItem);
            return cache[totalItems][maxWeightConstraint];
        } else {
            cache[totalItems][maxWeightConstraint] = maxProfitMemo(items, weights, maxWeightConstraint, totalItems - 1, cache);
            return cache[totalItems][maxWeightConstraint];
        }
    }
}