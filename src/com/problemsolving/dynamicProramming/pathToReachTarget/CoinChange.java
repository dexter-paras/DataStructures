/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.pathToReachTarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: CoinChange.java, v 0.1 2020-09-05 22:08 paras.chawla Exp $$
 */
public class CoinChange {

    List<List<Integer>> combinations = new ArrayList<>();
    int                 minCoins     = Integer.MAX_VALUE;

    // Approach 1 - Using recursion where coins can be repeated , Too slow
    public int leastCoins(int[] coins, int amount) {

        List<Integer> currResult = new ArrayList<>();
        Arrays.sort(coins);
        helper(coins, amount, currResult, 0);
        return combinations.size() == 0 ? -1 : minCoins;
    }

    // Getting all combinations of coins(can be duplicate as well) possible which adds to targetAmount
    private void helper(int[] coins, int target, List<Integer> currList, int idx) {

        // base condition
        if (target == 0) {
            // adding deep copy of currList
            combinations.add(new ArrayList<>(currList));
            minCoins = Math.min(minCoins, currList.size());
            return;
        }

        for (int i = idx; i < coins.length; i++) {
            if (target >= coins[i]) {
                currList.add(coins[i]);
                helper(coins, target - coins[i], currList, i);
                currList.remove(currList.size() - 1);
            }
        }
    }

    // Intuition is to find minimum number of coins required for each amount and store in cache
    // coins= [1,2,3] , amount =10
    public int coinChangeDp(int[] coins, int amount) {

        // 1. create cache which stores min number of coins needed to fulfil particular amount
        // 1.1 store all sub-problem
        // Sub-Problem - cache minimum number of coins needed to fulfil particular amount to get result for final amount
        int[] cache = new int[amount + 1];

        // 2. fill default dummy value
        // 2.1 why not with Integer.MAX_VALUE because adding anything in Integer.MAX_VALUE gives garbage result
        Arrays.fill(cache, amount+1);

        // 3. min number of coins needed to make amount =0 is always 0
        cache[0] = 0;

        // 4. solve every sub-problem from 1 to amount
        for (int amt = 1; amt < cache.length; amt++) {

            // 5. traverse coins array
            for (int j = 0; j < coins.length; j++) {

                // 5.1 If it is less than or equal to the sub problem amount
                if (amt >= coins[j]) {
                    cache[amt] = Math.min(cache[amt], 1 + cache[amt - coins[j]]);
                }
            }
        }
        // 6. if no result then cache[amount] = amount+1 (initialize during starting)
        return cache[amount] > amount ? -1 :cache[amount];
    }

    public static void main(String[] args) {
        CoinChange obj = new CoinChange();
        long startTime = System.currentTimeMillis();
        System.out.println(obj.coinChangeDp(new int[] {3,4}, 5));
        /*
        System.out.println(obj.combinations);
        System.out.println("Number of Combinations: "+ obj.combinations.size());
        System.out.println(obj.minCoins);*/
        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken" + (endTime - startTime));
    }

}