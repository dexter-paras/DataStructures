/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: MaxSizeSubarrayLengthK.java, v 0.1 2020-11-05 23:53 paras.chawla Exp $$
 * <p>
 * 325. Maximum Size Subarray Sum Equals k https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 * <p>
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 * <p>
 * Note: The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1, -1, 5, -2, 3], k = 3 Output: 4 Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 */
public class MaxSizeSubarrayLengthK {

    public int maxSubArrayLen(int[] nums, int k) {

        // Map<currentSum,idx>
        // store cSum and cIdx in Map and use later to find bracket where sum= target
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;

        // max subArray size with sum =k
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            // check if sum ==k
            if (sum == k) {
                max = i + 1;
            }
            // found previous idx where sum = sum -k and hence Target sum must exist
            // in between previousIdx and currentIdx
            else if (map.containsKey(sum - k)) {
                max = Math.max(max, i - map.get(sum - k));
            }
            // put currentSum as key and currentIdx as Value if not already exist
            map.putIfAbsent(sum, i);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSizeSubarrayLengthK obj = new MaxSizeSubarrayLengthK();
        System.out.println(obj.maxSubArrayLen(new int[] {1,-1,5,-2,3}, 3));
        System.out.println(obj.maxSubArrayLen(new int[] {-2, -1, 2, 1}, 1));
    }

}