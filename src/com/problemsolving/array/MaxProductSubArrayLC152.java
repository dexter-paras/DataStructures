/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.array;

/**
 * @author paras.chawla
 * @version $Id: MaxProductSubArrayLC152.java, v 0.1 2019-10-10 07:26 paras.chawla Exp $$
 */
public class MaxProductSubArrayLC152 {

    // nums[] = {2,3,-2,4}
    public int maxProduct(int[] nums) {
        if (isArrayPositiveOrEvenNegative(nums)) {
            return multiplyArray(nums);
        } else {
            int maxProduct = 1;
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    maxProduct = nums[i] * maxProduct;
                    int a = maxProduct * nums[j];
                }
            }
        }
        return 0;
    }

    private int multiplyArray(int[] nums) {
        int maxProduct = 1;
        for (int i : nums) {
            maxProduct *= nums[i];
        }
        return maxProduct;
    }

    // nums[] = {2,3,-2,4}
    private boolean isArrayPositiveOrEvenNegative(int[] nums) {
        int negativeCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0)
                negativeCount++;
        }
        return negativeCount % 2 == 0;
    }


    int maxProduct2(int A[], int n) {
        // store the result that is the max we have found so far
        int r = A[0];

        // imax/imin stores the max/min product of
        // subarray that ends with the current number A[i]
        for (int i = 1, imax = r, imin = r; i < n; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (A[i] < 0)
                swap(imax, imin);

            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            imax = Integer.max(A[i], imax * A[i]);
            imin = Integer.min(A[i], imin * A[i]);

            // the newly computed max value is a candidate for our global result
            r = Integer.max(r, imax);
        }
        return r;
    }

    private void swap(int imax, int imin) {
        int temp = imax;
        imax = imin;
        imin = temp;
    }

    public static void main(String[] args) {
        int num[] = new int[]{2, 3, -2, 4};
        new MaxProductSubArrayLC152().maxProduct2(num, num.length);
    }
}