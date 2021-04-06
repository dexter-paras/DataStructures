/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.recursion;

/**
 * @author paras.chawla
 * @version $Id: MaxConsecutiveSums.java, v 0.1 2021-03-08 3:14 PM paras.chawla Exp $$
 */
public class MaxConsecutiveSums {

    public static void main(String[] args) {
        MaxConsecutiveSums obj = new MaxConsecutiveSums();
        int[] A = new int[] {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int[] B = new int[] {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        System.out.println(obj.longestOnes2(A, 2));
        System.out.println(obj.longestOnes2(B, 3));
    }

    // Approach 1 - https://leetcode.com/problems/max-consecutive-ones-iii/discuss/249270/Java-DFS-solution-using-Recursion
    // Intuition - Calculate maxConsecutiveSum for all subarrays in array keeping k in mind
    // 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0
    // 1, 1, 0, 0, 0, 1, 1, 1, 1, 0
    // 1, 0, 0, 0, 1, 1, 1, 1, 0
    // 0, 0, 0, 1, 1, 1, 1, 0
    // 0, 0, 1, 1, 1, 1, 0
    // 0, 1, 1, 1, 1, 0
    // 1, 1, 1, 1, 0
    // 1, 1, 1, 0
    // 1, 1, 0
    // 1, 0
    // 0
    public int longestOnes(int[] A, int K) {
        int rL = 0;
        int longest = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            rL = dfs(A, i, 0, K);
            longest = Math.max(longest, rL);
        }

        return longest;
    }

    private static int dfs(int[] A, int index, int runningLength, int K) {
        if (index >= A.length || K < 0) {
            return runningLength;
        }

        //keep scanning ahead without decrementing K
        if (A[index] == 1) {
            runningLength++;
            return dfs(A, index + 1, runningLength, K);
        }

        //scan ahead and decrement K
        if (A[index] == 0 && K > 0) {
            runningLength++;
            return dfs(A, index + 1, runningLength, K - 1);
        }

        return runningLength;
    }

    // Approach 2 - Instead of working on all subarrays, take two pointers - left and right pointer
    public int longestOnes2(int[] A, int K) {

        int maxOnes = Integer.MIN_VALUE;

        for (int L = 0, R = 0; L < A.length && R < A.length; L++) {

            // Expanding R pointer
            while (R < A.length && (A[R] == 1 || K > 0)) {
                if (A[R] == 0) {
                    K--;
                }
                R++;
            }

            // window not following property
            maxOnes = Math.max(maxOnes, R - L);

            if (A[L] == 0) {
                K++;
            }
        }

        return maxOnes;
    }
}