/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.dpOnString;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: LongestPalindromicSubSequence.java, v 0.1 2020-05-09 21:30 paras.chawla Exp $$
 */
public class LongestPalindromicSubSequence {

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // Creating dp array to store lps of particular substring(start and end index)
        // so as to avoid recomputation

        int dp[][] = new int[s.length()][s.length()];
        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }

        return helper(s, 0, s.length() - 1);
    }

    private int helper(String s, int start, int end) {

        // Every string itself is a palindrome of itself
        if (start == end) {
            return 1;
        }

        if (start > end) {
            return 0;
        }

        if (s.charAt(start) == s.charAt(end)) {
            return 2 + helper(s, start+1, end-1);
        } else {
            return Math.max(helper(s, start, end-1), helper(s, start+1, end));
        }
    }

    private int helperDp(String s, int start, int end, int[][] dp) {

        // Every string itself is a palindrome of itself
        if (start == end) {
            return 1;
        }

        if (start > end) {
            return 0;
        }

        if (dp[start][end] > -1) {
            return dp[start][end];
        }

        int result = 0;

        if (s.charAt(start) == s.charAt(end)) {
            result = 2 + helperDp(s, start+1, end-1, dp);
        } else {
            result = Math.max(helperDp(s, start, end-1, dp), helperDp(s, start+1, end, dp));
        }

        dp[start][end] = result;

        return result;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println(new LongestPalindromicSubSequence().longestPalindromeSubseq("adbgcaabbccfbea"));
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in nanoseconds  : " + timeElapsed);

        System.out.println("Execution time in milliseconds : " +
                timeElapsed / 1000000);
    }

}