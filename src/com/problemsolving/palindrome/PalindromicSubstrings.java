/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.palindrome;

/**
 * @author paras.chawla
 * @version $Id: PalindromicSubstrings.java, v 0.1 2020-05-04 00:53 paras.chawla Exp $$
 */
public class PalindromicSubstrings {
    int count = 0;

    // Approach -1 Brute Force
    public int countSubstrings(String s) {

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            count++;
            StringBuffer buffer = new StringBuffer();
            buffer.append(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                buffer.append(s.charAt(j));
                if (isPalindrome(buffer)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPalindrome(StringBuffer buffer) {
        for (int i = 0, j = buffer.length() - 1; i <= (buffer.length() - 1) / 2; i++, j--) {
            if (buffer.charAt(i) != buffer.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //System.out.println(new PalindromicSubstrings().countSubstrings("abc"));
        System.out.println(new PalindromicSubstrings().countSubstrings2("NITIN"));
        System.out.println(new PalindromicSubstrings().countSubstrings2("ABCCBA"));
    }

    // Approach 2- EXTEND PALINDROME
    // https://leetcode.com/problems/palindromic-substrings/discuss/105688/Very-Simple-Java-Solution-with-Detail-Explanation
    public int countSubstrings2(String s) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            count += expandArroundCenter(s, i, i); // To check the palindrome of odd length palindromic sub-string
            count += expandArroundCenter(s, i, i + 1); // To check the palindrome of even length palindromic sub-string

        }
        return count;
    }

    //"NITIN"
    private int expandArroundCenter(String str, int left, int right) {
        int ans = 0;
        StringBuilder builder = new StringBuilder();
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            ans++;
            left--; //To trace string in left direction
            right++; //To trace string in right direction
            if (left == right) {
                builder.append(str.charAt(left));
            } else {
                builder.insert(0, str.charAt(left)).append(str.charAt(right));
            }
        }
        return ans;
    }

    // Approach 3- Using Dynamic Programming
    // Single character is always palindromic of itself
    // for 2 character, check if first ch == second character
    // for > 2 characters, if first ch == last char and mid string is already palindromic
    // https://www.youtube.com/watch?v=EIf9zFqufbU
    public int countSubString3(String s) {
        if (s == null || s.length() == 0) { return 0; }

        int length = s.length();

        int[][] dp = new int[length][length];
        int count = 0;

        // Single character is always palindromic of itself
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
            count++;
        }

        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                // For substring of length 2
                if (s.charAt(i) == s.charAt(j) && (Math.abs(i - j) == 1 || dp[i + 1][j - 1] == 1)) {
                    dp[i][j] = 1;
                    count++;
                }
            }
        }
        return count;
    }

}