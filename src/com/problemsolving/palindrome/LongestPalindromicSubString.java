/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.palindrome;

/**
 * @author paras.chawla
 * @version $Id: LongestPalindromicSubString.java, v 0.1 2020-05-06 00:12 paras.chawla Exp $$
 */
public class LongestPalindromicSubString {

    public String longestPalindrome(String s) {
        int n = s.length();
        StringBuilder oddLengthString = new StringBuilder();
        StringBuilder evenLengthString = new StringBuilder();
        String longestString = new String();
        for (int i = 0; i < n; i++) {
            oddLengthString = expandArroundCenter(s, i, i); // To check the palindrome of odd length palindromic sub-string
            evenLengthString = expandArroundCenter(s, i, i + 1); // To check the palindrome of even length palindromic sub-string
            if (oddLengthString.length() > evenLengthString.length() && oddLengthString.length() > longestString.length()) {
                longestString = oddLengthString.toString();
            } else if (evenLengthString.length() > oddLengthString.length() && evenLengthString.length() > longestString.length()) {
                longestString = evenLengthString.toString();
            }
        }
        return longestString;
    }

    //"NITIN"
    private StringBuilder expandArroundCenter(String str, int left, int right) {
        StringBuilder builder = new StringBuilder();
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            if (left == right) {
                builder.append(str.charAt(left));
            } else {
                builder.insert(0, str.charAt(left)).append(str.charAt(right));
            }
            left--; //To trace string in left direction
            right++; //To trace string in right direction
        }
        return builder;
    }

    public static void main(String[] args) {
        //System.out.println(new PalindromicSubstrings().countSubstrings("abc"));
        System.out.println(new LongestPalindromicSubString().longestPalindrome("NITIN"));
        //System.out.println(new PalindromicSubstrings().countSubstrings2("ABCCBA"));
    }

}