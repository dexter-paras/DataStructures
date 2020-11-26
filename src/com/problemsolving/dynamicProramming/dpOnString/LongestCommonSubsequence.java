/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.dpOnString;

/**
 * @author paras.chawla
 * @version $Id: LongestCommonSubsequence.java, v 0.1 2020-03-25 09:58 paras.chawla Exp $$
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        //System.out.println(new LongestCommonSubsequence().longestCommonSubsequenceRecursion("AGGTAB", "GXTXAYB"));
        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("GXTXAYB", "AGGTAB"));
    }

    // Approach 1 - Using Recursion, - TOP DOWN Approach; Not good for str > 10
    private int longestCommonSubsequenceRecursion(String str1, String str2) {
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return 0;
        }
        return helper(0, str1, 0, str2);
    }

    private int helper(int i1, String str1, int i2, String str2) {
        if (str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        if (i1 >= str1.length() || i2 >= str2.length()) {
            return 0;
        }
        // if both characters matches
        if (str1.charAt(i1) == str2.charAt(i2)) {
            return 1 + helper(i1 + 1, str1, i2 + 1, str2);
        } else {
            return Math.max(helper(i1, str1, i2 + 1, str2), helper(i1 + 1, str1, i2, str2));
        }
    }

    // Approach 2 - Using Dynamic Programming
    // text1 ="GXTXAYB" and text2="AGGTAB"
    private int longestCommonSubsequence(String text1, String text2) {
        int dp[][] = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}