/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.dpOnString;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: LongestPalindromeSubstringLC5.java, v 0.1 2019-08-05 09:09 paras.chawla Exp $$
 */
public class LongestPalindromeSubstringLC5 {
    private int longestPalindrome(String s) {
        int array[] = new int[s.length()];
        return longestPalindromeSubsequenceFromArray(array);
    }

    private int longestPalindromeSubsequenceFromArray(int array[]) {
        return Arrays.stream(array).max().getAsInt();
    }

    // index=0 , s="nitin" , char ='n' , left =-1 , right =1
    // index=1 , s="nitin" , char ='i' , left =0, right=2
    // index=2 , s="nitin" , char ='t' , left =1, right=3

    // s="ABBACBDBCA"
    private int expandOdd(int index, String s) {
        int left = index - 1; //1
        int right = index + 1; //3
        int count = 0;
        for (; left >= 0 && right < s.length(); --left, right++) {
            if (s.charAt(left) == s.charAt(right)) {
                count = count + 2;
            }
        }
        if (count > 1) {
            count++;
        }
        return count;
    }

    /*Longest Palindromic substring using DP
     * Intent is to reduce O(n) complexity of finding palindrome in a substring
        A B B A
      A T F F T
      B   T T F
      B     T F
      A       T
     */
    public String longestPalindromeUsingDP(String s) {

        if (s == null || s.isEmpty()) {
            return s;
        }
        boolean[][] palindromeTable = new boolean[s.length()][s.length()];

        /* All substrings of length 1 is palindrome */
        int maxLength = 1;
        for (int i = 0; i < s.length(); i++) {
            palindromeTable[i][i] = true;
        }

        // Check for sub-string of length 2
        int start = 0;
        for (int i = 0; i < s.length() - 1; ++i) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                palindromeTable[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }

        /* check for substring of length greater than 2
           where k is length of substring */
        for (int k = 3; k <= s.length(); k++) {
            // k=3 , i=0 to 4-3+1=2 ,j=i+k-1 =0+3-1=2    02 ; 13
            // k=4 , i=0 to 4-4+1=1 ,j=i+k-1             03
            for (int i = 0; i < s.length() - k + 1; i++) {
                int j = i + k - 1;
                if (s.charAt(i) == s.charAt(j) && palindromeTable[i + 1][j - 1]) {
                    palindromeTable[i][j] = true;
                    if (k > maxLength) {
                        start = i;
                        maxLength = k;
                    }
                }
            }
        }
        return printSubStr(s, start, start + maxLength - 1);
    }

    // A utility function to print a substring str[low..high]
    static String printSubStr(String str, int low, int high) {
        return str.substring(low, high + 1);
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindromeSubstringLC5().longestPalindromeUsingDP(""));
    }
}