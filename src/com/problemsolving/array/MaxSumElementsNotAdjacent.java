/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.array;

/**
 * @author paras.chawla
 * @version $Id: MaxSumElementsNotAdjacent.java, v 0.1 2021-01-31 8:27 AM paras.chawla Exp $$
 */
public class MaxSumElementsNotAdjacent {

    static int maxSum(int[] array) {

        int maxSum[] = new int[array.length];

        maxSum[0] = array[0];
        maxSum[1] = array[1];

        for (int i = 2; i < array.length; i++) {
            maxSum[i] = Math.max(maxSum[i - 1], array[i] + maxSum[i - 2]);
        }
        return maxSum[maxSum.length - 1];
    }
}