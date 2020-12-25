/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.recursion;

/**
 * @author paras.chawla
 * @version $Id: ClimbingStairs.java, v 0.1 2020-12-19 20:56 paras.chawla Exp $$
 * Intuition - Total number of ways to reach nth stairCase
 * using 1 step or 2 step = Total number of ways to reach n-1th stairCase using 1 or 2 step
 * + Total number of ways to reach n-2th stairCase using 1 or 2 step
 *
 * https://leetcode.com/problems/climbing-stairs/solution/
 */
public class ClimbingStairs {

    int count = 0;

    public int climbStairs(int n) {
        int[] steps = new int[] {1, 2, 3};
        helper(n, steps);
        return count;
    }

    void helper(int n, int[] steps) {
        if (n == 0) {
            count++;
            return;
        }

        for (int i = 0; i < steps.length; i++) {
            n -= steps[i];
            if (n < 0) {
                break;
            }
            helper(n, steps);
            n += steps[i];
        }
    }

    public int climbStairsDp(int n) {

        int[] dp = new int[n + 1];

        // Total number of ways to reach 0th staircase using 1 or 2 step is 1
        dp[0] = 1;
        // Total number of ways to reach 1th staircase using 1 or 2 step is 1
        dp[1] = 1;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        ClimbingStairs obj = new ClimbingStairs();
        System.out.println(obj.climbStairsDp(6));
    }

}