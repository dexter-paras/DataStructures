/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.slidingwindow;

/**
 * @author paras.chawla
 * @version $Id: MinimumSizeSubarraySum.java, v 0.1 2020-11-07 23:57 paras.chawla Exp $$
 * This solution will work for positive numbers only
 */
public class MinimumSizeSubarraySum {

    // Input: s = 7, nums = [2,3,1,2,4,3]
    public int minSubArrayLen(int s, int[] nums) {

        int start = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        // nums[] = [2,3,1,2,4,3]
        //                     ^
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];//sum=9
            while (sum >= s) {
                min = Math.min(min, i - start + 1); //2
                sum -= nums[start];//4
                start++;
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum obj = new MinimumSizeSubarraySum();
        System.out.println(obj.minSubArrayLen(167, new int[] {84,-37,32,40,95}));
    }
}