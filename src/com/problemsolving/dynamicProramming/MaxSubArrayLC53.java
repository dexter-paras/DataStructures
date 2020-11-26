/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming;

/**
 * @author paras.chawla
 * @version $Id: MaxSubArrayLC53.java, v 0.1 2019-10-15 08:07 paras.chawla Exp $$
 * Description : Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * Input: [-2,1,-3,4,-1,2,1,-5,4]
 *        [-2,-1, , ,  ,  , ,  , ]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class MaxSubArrayLC53 {

    public int maxSubArray(int[] nums) {

        if (nums.length == 0)
            return 0;

        // initialize array which store max contiguous subarray sum at index i
        int[] dpArr = new int[nums.length];
        dpArr[0] = nums[0];
        int maxSum = dpArr[0];

        // At each index , finding whether to add number at index i in previous sum or start with element at index i
        for (int i = 1; i < nums.length; i++) {
            dpArr[i] = Math.max(nums[i], dpArr[i - 1] + nums[i]);
            maxSum = dpArr[i] > maxSum ? dpArr[i] : maxSum;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int nums[] = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new MaxSubArrayLC53().maxSubArray(nums));
    }
}