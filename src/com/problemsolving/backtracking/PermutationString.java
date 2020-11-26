/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: PermutationString.java, v 0.1 2020-04-11 22:56 paras.chawla Exp $$
 */

// To find all permutations of a String str="par"
public class PermutationString {

    /*
    Logic - We need to fix 1 char and find permutation of another chars
    * Moving 1 by 1 character from suffix to prefix till suffix length is 0
    *
    * "" - prefix , "par" - suffix
    * permutation("","par")
    * permutation("p","ar")
    * permutation("pa","r")  &&   permutation("pr","a")
    * permutation("par","")  &&   permutation("pra","")
    * Base condition - if suffix.length==0, that means permutation of string found
    * */
    private List<String> permutation(String str) {

        List<String> results = new ArrayList<>();
        System.out.println(permutation("", str, results));
        return results;
    }

    private void permutationSwap(String str) {
        permutationSwap(str, 0, str.length() - 1);
    }

    // ("Par")
    private void permutationSwap(String str, int l, int r) {
        if (l == r) {
            System.out.println(str);
        }
        /* l=0,r=2
           after swap str="par"
           l=1,r=2
           after swap str="par"
           l=2,r=2
           after swap str="par"
        */
        for (int i = l; i <= r; i++) {
            str = swap(str, l, i);
            permutationSwap(str, l + 1, r);
            str = swap(str, l, i);
        }
    }

    // "abc" ,l=0,r=1 ->bac
    private String swap(String str, int l, int r) {
        char temp;
        char[] charArray = str.toCharArray();
        temp = charArray[l];//a
        charArray[l] = charArray[r];
        charArray[r] = temp;
        return String.valueOf(charArray);

    }

    //("","par",results)
    private List<String> permutation(String prefix, String suffix, List<String> results) {

        if (suffix.length() == 0) {
            results.add(prefix);
        } else {
            for (int i = 0; i < suffix.length(); i++) {
                /*    i=0         ("","par")  suffix ="par"
                     i=0       ("p","ar") i=1  suffix ="ar"
                     i=0   ("pa","r")    ("pr","a")     suffix ="r"
                    i=0 ("par","")          ("pra","")      suffix =""

                 i=1          ("a","pr")
                        ("ap","r")    ("ar","p")
                    ("apr","")          ("arp","")

                 i=2         ("r","pa")
                        ("rp","a")  ("ra","p")
                    ("rpa","")          ("rap","")
                */
                permutation(prefix + suffix.charAt(i), suffix.substring(0, i)
                        + suffix.substring(i + 1, suffix.length()), results);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        new PermutationString().permutation("para");
        //new PermutationString().permutationSwap("par");
    }
}