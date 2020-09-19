/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.zupeek;

import java.util.Scanner;

/**
 * @author paras.chawla
 * @version $Id: SubarraysWithSumZero.java, v 0.1 2020-09-06 21:33 paras.chawla Exp $$
 */
public class SubarraysWithSumZero {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int testCases, input;
        testCases = in.nextInt();
        input= in.nextInt();

        int[] numbers = new int[input];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = in.nextInt();
        }
        System.out.println(countAllSubArrays(numbers));
    }

    private static int countAllSubArrays(int[] A) {

        int count=0;

        // consider all sub-arrays starting from i
        for (int i = 0; i < A.length; i++)
        {
            int sum = 0;

            // consider all sub-arrays ending at j
            for (int j = i; j < A.length; j++)
            {
                // sum of elements so far
                sum += A[j];

                // if sum is seen before, we have found a subarray with 0 sum
                if (sum == 0) {
                    count++;
                }
            }
        }
        return count;
    }

}