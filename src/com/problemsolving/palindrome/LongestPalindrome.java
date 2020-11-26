/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.palindrome;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: LongestPalindrome.java, v 0.1 2020-08-17 23:22 paras.chawla Exp $$
 * <p>
 * Input: s = "abccccdd" Output: 7 Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {

    // str= "ntint" -> "nitin"
    public int longestPalindrome(String str) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (set.contains(ch)) {
                set.remove(ch);
                count++;
            } else {
                set.add(ch);
            }
        }
        return set.isEmpty() ? count * 2 : count * 2 + 1;
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome("bb"));
    }
}