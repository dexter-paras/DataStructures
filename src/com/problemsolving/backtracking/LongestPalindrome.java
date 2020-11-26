/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: LongestPalindrome.java, v 0.1 2020-08-16 21:37 paras.chawla Exp $$
 */
public class LongestPalindrome {

    private Set<String> permutationAll(String prefix, String suffix, Set<String> results) {

        if (!prefix.isEmpty()) {
            results.add(prefix);
        }
        for (int i = 0; i < suffix.length(); i++) {
                /*i=0        ("p","ar")
                        ("pa","r")    ("pr","a")
                    ("par","")          ("pra","")

                 i=1          ("a","pr")
                        ("ap","r")    ("ar","p")
                    ("apr","")          ("arp","")

                 i=2         ("r","pa")
                        ("rp","a")  ("ra","p")
                    ("rpa","")          ("rap","")
                */
            permutationAll(prefix + suffix.charAt(i), suffix.substring(0, i)
                    + suffix.substring(i + 1, suffix.length()), results);
        }
        return results;
    }

    private Set<String> permutation(String str) {

        Set<String> results = new HashSet<>();
        return permutationAll("", str, results);
    }

    public static void main(String[] args) {
        Set<String> set = new LongestPalindrome().permutation("tattarrattat");
        String longestPalindrome = "";
        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()) {
            String str = iterator.next();
            if (isPalindrome(str, 0, str.length() - 1) && str.length() > longestPalindrome.length()) {
                longestPalindrome = str;
            }
        }

        System.out.println("Longest Palindrome is :" + longestPalindrome);

        //new PermutationString().permutationSwap("par");
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
