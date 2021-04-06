/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: PalindromePartition.java, v 0.1 2020-11-27 15:02 paras.chawla Exp $$
 */
public class PalindromePartition {

    List<List<String>> globalResult = new ArrayList<>();


    // Intuition - Divide String into all possible substrings and continue dfs only if its a valid Palindrome
    public List<List<String>> partition(String str) {

        List<String> result = new ArrayList<>();
        if (str.isEmpty()) {
            return globalResult;
        }

        partition(str, result);

        return globalResult;
    }

    // str ="abba"
    private void partition(String str, List<String> result) {

        // base condition
        if (str.isEmpty()) {
            globalResult.add(new ArrayList<>(result));
        }

        for (int i = 0; i < str.length(); i++) {

            if (isPalindrome(str.substring(0, i + 1))) {

                // Choose
                result.add(str.substring(0, i + 1));

                // recurse
                partition(str.substring(i + 1), result);

                // Unchoose
                result.remove(result.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String buffer) {
        for (int i = 0, j = buffer.length() - 1; i <= (buffer.length() - 1) / 2; i++, j--) {
            if (buffer.charAt(i) != buffer.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    // Approach 2- Using DP
    // Removed isPalindrome method and replaced with already created dp array which tells if string is palindrome or not
    public List<List<String>> partitionDp(String s) {
        List<List<String>> res = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                }
            }
        }
        helper(res, new ArrayList<>(), dp, s, 0);
        return res;
    }

    private void helper(List<List<String>> res, List<String> path, boolean[][] dp, String s, int pos) {
        if (pos == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = pos; i < s.length(); i++) {
            if (dp[pos][i]) {
                path.add(s.substring(pos, i + 1));
                helper(res, path, dp, s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PalindromePartition obj = new PalindromePartition();
        System.out.println(obj.partitionDp("abbcbba"));
    }

}