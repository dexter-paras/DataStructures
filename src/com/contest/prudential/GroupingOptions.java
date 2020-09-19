/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.prudential;

/**
 * @author paras.chawla
 * @version $Id: GroupingOptions.java, v 0.1 2020-09-16 23:13 paras.chawla Exp $$
 * https://www.geeksforgeeks.org/count-the-number-of-ways-to-divide-n-in-k-groups-incrementally/
 */
public class GroupingOptions {

    // Cache Table
    static int[][][] cache = new int[201][201][201];

    // to count number of ways to divide people in groups
    public static long countOptions(int people, int groups) {
        return countWaystoDivide(people, groups);
    }

    // fill cache table to 201 as per constraint given
    static int countWaystoDivide(int n, int k) {
        // Intialize DP Table as -1
        for (int i = 0; i < 201; i++) {
            for (int j = 0; j < 201; j++) {
                for (int l = 0; l < 201; l++) { cache[i][j][l] = -1; }
            }
        }
        return dividePeople(0, 1, n, k);
    }

    // recursive top down + memoization
    static int dividePeople(int pos, int prev,
                            int left, int k) {
        // base condition
        if (pos == k) {
            if (left == 0) { return 1; } else { return 0; }
        }

        // if people is divided completely into less than k groups
        if (left == 0) { return 0; }

        // check cache If the subproblem has been solved, use the value
        if (cache[pos][prev][left] != -1) { return cache[pos][prev][left]; }

        int answer = 0;

        // put all possible values
        // greater equal to prev
        for (int i = prev; i <= left; i++) {
            answer += dividePeople(pos + 1, i,
                    left - i, k);
        }
        return cache[pos][prev][left] = answer;
    }
}