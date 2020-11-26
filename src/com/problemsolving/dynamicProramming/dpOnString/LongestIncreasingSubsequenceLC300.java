/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.dpOnString;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: LongestIncreasingSubsequenceLC300.java, v 0.1 2019-10-14 07:11 paras.chawla Exp $$
 */
public class LongestIncreasingSubsequenceLC300 {

    public int lengthOfLIS(int[] nums) {

        // if length is 0, LIS=0
        if (nums.length == 0) { return 0; }

        /* initialize array to store our sub-problems
        Each index records the answer to "what is the longest increasing
        subsequence ending at index i of the original array?" */
        int[] T = new int[nums.length];

        // fill array with default value 1
        Arrays.fill(T, 1);

        for (int j = 1; j < nums.length; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[j] > nums[i]) {
                    T[j] = Math.max(T[j], T[i] + 1);
                }
            }
        }
        return maxElementInArray(T);
    }

    private int maxElementInArray(int[] T) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < T.length; i++) {
            max = T[i] > max ? T[i] : max;
        }
        return max;
    }

    private int longestIncreasingSubsequence(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        return helper(nums, 0);
    }

    private int helper(int[] nums, int i) {

        if (i >= nums.length - 1) {
            return 0;
        }
        if (nums[i + 1] >= nums[i]) {
            return 1 + helper(nums, i + 1);
        } else {
            return helper(nums, i + 1);
        }
    }

    public int LIS(int[] arr) {
        return LISLength(arr, Integer.MIN_VALUE, 0);
    }

    public int LISLength(int[] arr, int prev, int current) {
        if (current == arr.length) {
            return 0;
        }
        int include = 0;
        if (arr[current] > prev) {
            include = 1 + LISLength(arr, arr[current], current + 1);
        }
        int exclude = LISLength(arr, prev, current + 1);
        return Math.max(include, exclude);
    }

    public int lengthOfLIS2(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        // length of longestIncreaseingSubsequencet at index i
        int dp[] = new int[nums.length];

        // By default LIS at each index i is 1
        Arrays.fill(dp, 1);

        // By default,best answer is length of 1
        int result = 1;
        //nums[]= [10,9,2,5,3,7,101,18]
        //  dp[]= [01,1,1,2,2,3,4,5]
        // checking at index=3,element 5
        // Check if 5>10 , 5>9, 5>2(YES), include to LIS result

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {

                // contribute to the previous sum
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            result = Math.max(dp[i], result);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] A = new int[] {10, 9, 2, 5, 3, 7, 101, 18};
        //System.out.println(new LongestIncreasingSubsequenceLC300().lengthOfLIS(A));
        //System.out.println(new LongestIncreasingSubsequenceLC300().longestIncreasingSubsequence(A));
        //System.out.println(new LongestIncreasingSubsequenceLC300().LIS(A));
        System.out.println(new LongestIncreasingSubsequenceLC300().lengthOfLIS2(A));

    }
}