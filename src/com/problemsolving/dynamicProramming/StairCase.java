/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming;

import java.time.Duration;
import java.time.Instant;

/**
 * @author paras.chawla
 * @version $Id: StairCase.java, v 0.1 2019-09-20 22:48 paras.chawla Exp $$
 * description - To find number of combinations to reach n staircase using 1 step or 2 step at a time
 */
public class StairCase {

    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairsIterative(int n) {

        Instant start = Instant.now();
        int array[] = new int[n + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        Instant finish = Instant.now();
        System.out.println("Time Taken in Iterative " + Duration.between(start, finish).toMillis());

        return array[n];
    }

    public static void main(String[] args) {
        System.out.println("Recursive ");
        Instant start = Instant.now();
        System.out.println(new StairCase().climbStairs(35));
        Instant finish = Instant.now();
        System.out.println("Time Taken in Recursive " + Duration.between(start, finish).toMillis());

        System.out.println("Iterative ");
        System.out.println(new StairCase().climbStairsIterative(35));
    }

}