/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.recursion;

import java.util.HashMap;

/**
 * @author paras.chawla
 * @version $Id: PaintHouse.java, v 0.1 2020-11-23 12:58 paras.chawla Exp $$ https://leetcode.com/problems/paint-house/
 * https://leetcode.com/problems/paint-house/solution/
 */
public class PaintHouse {

    // Approach -1 Solving using Recursive tree
    public int minCost(int[][] costs) {

        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        // Minimum of
        // Total cost incur of painting house 0 with color 0
        // Total cost incur of painting house 0 with color 1
        // Total cost incur of painting house 0 with color 2]
        return Math.min(Math.min(minTotalCost(0, 0, costs), minTotalCost(0, 1, costs)), minTotalCost(0, 2, costs));
    }

    private int minTotalCost(int house, int color, int[][] costs) {
        int totalCost = costs[house][color];

        // base condition - return totalCost
        if (house == costs.length - 1) {
        } else if (color == 0) {
            totalCost += Math.min(minTotalCost(house + 1, 1, costs), minTotalCost(house + 1, 2, costs));
        } else if (color == 1) {
            totalCost += Math.min(minTotalCost(house + 1, 0, costs), minTotalCost(house + 1, 2, costs));
        } else if (color == 2) {
            totalCost += Math.min(minTotalCost(house + 1, 0, costs), minTotalCost(house + 1, 1, costs));
        }
        return totalCost;
    }

    // Approach -2 Solving using Recursive tree + Memoization(Storing result in HashMap so as to prevent from recomputation
    public int minCost2(int[][] costs) {

        HashMap<String, Integer> map = new HashMap<>();
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        // Minimum of
        // Total cost incur of painting house 0 with color 0
        // Total cost incur of painting house 0 with color 1
        // Total cost incur of painting house 0 with color 2]
        return Math.min(Math.min(minTotalCost2(0, 0, costs, map), minTotalCost2(0, 1, costs, map)), minTotalCost2(0, 2, costs, map));
    }

    private int minTotalCost2(int house, int color, int[][] costs, HashMap<String, Integer> map) {
        int totalCost = costs[house][color];
        String key = createKey(house, color);

        // If computation already done for house and color, then return right away
        if (map.containsKey(key)) {
            return map.get(key);
        }

        // base condition - return totalCost
        if (house == costs.length - 1) {
        } else if (color == 0) {
            totalCost += Math.min(minTotalCost2(house + 1, 1, costs, map), minTotalCost2(house + 1, 2, costs, map));
        } else if (color == 1) {
            totalCost += Math.min(minTotalCost2(house + 1, 0, costs, map), minTotalCost2(house + 1, 2, costs, map));
        } else if (color == 2) {
            totalCost += Math.min(minTotalCost2(house + 1, 0, costs, map), minTotalCost2(house + 1, 1, costs, map));
        }

        map.put(createKey(house, color), totalCost);
        return totalCost;
    }

    private String createKey(int house, int color) {
        return String.valueOf(house) + " " + String.valueOf(color);
    }

    // Approach 3 - Dynamic Programming - Iterative Bottom-Up Approach

    public int minCost3(int[][] costs) {

        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        for (int i = costs.length - 2; i >= 0; i--) {
            // Total cost of painting the ith house red.
            costs[i][0] += Math.min(costs[i + 1][1], costs[i + 1][2]);
            // Total cost of painting the nth house Green.
            costs[i][1] += Math.min(costs[i + 1][0], costs[i + 1][2]);
            // Total cost of painting the nth house Blue.
            costs[i][2] += Math.min(costs[i + 1][0], costs[i + 1][1]);
        }

        return Math.min(Math.min(costs[0][0], costs[0][1]), costs[0][2]);

    }

    public static void main(String[] args) {
        PaintHouse obj = new PaintHouse();
        int[][] costs = new int[][] {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        //System.out.println(obj.minCost(costs));
        System.out.println(obj.minCost3(costs));
    }
}