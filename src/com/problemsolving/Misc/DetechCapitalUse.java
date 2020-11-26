/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Misc;

/**
 * @author paras.chawla
 * @version $Id: DetechCapitalUse.java, v 0.1 2020-08-01 22:54 paras.chawla Exp $$
 */
public class DetechCapitalUse {

    public boolean detectCapitalUse(String word) {
        char[] array = word.toCharArray();

        //All letters in this word are capitals, like "USA"
        int allCapitalCount = 0;
        int allSmallCount = 0;
        int firstSmallCount = 1;
        for (char ch : array) {
            if (ch >= 'A' && ch <= 'Z') {
                allCapitalCount++;
            }
        }

        //All letters in this word are small, like "leetcode"
        for (char ch : array) {
            if (ch >= 'a' && ch <= 'z') {
                allSmallCount++;
            }
        }

        //Only the first letter in this word is capital, like "Google"
        if (array[0] >= 'A' && array[0] <= 'Z') {
            for (int i = 1; i < array.length; i++) {
                if (array[i] >= 'a' && array[i] <= 'z') {
                    firstSmallCount++;
                }
            }
        }
        if (allCapitalCount == word.length() || allSmallCount == word.length() || firstSmallCount == word.length()) {
            return true;
        }

        return false;
    }
}