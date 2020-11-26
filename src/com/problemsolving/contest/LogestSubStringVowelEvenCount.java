/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.contest;

/**
 * @author paras.chawla
 * @version $Id: LogestSubStringVowelEvenCount.java, v 0.1 2020-03-07 20:06 paras.chawla Exp $$
 */
public class LogestSubStringVowelEvenCount {

    public static void main(String[] args) {
        new LogestSubStringVowelEvenCount().findTheLongestSubstring("leetcodeisgreat");
    }

    public int findTheLongestSubstring(String s) {
        StringBuffer previousLongestString= new StringBuffer();
        for (int i = 0; i < s.length() - 1; i++) {
            previousLongestString = new StringBuffer(String.valueOf(s.charAt(i)));
            StringBuffer str = new StringBuffer(String.valueOf(s.charAt(i)));
            for (int j = i + 1; j < s.length(); j++) {
                StringBuffer newSubString = str.append(s.charAt(j));
                boolean longestSubStringWithEvenVowels = longestSubString(newSubString, previousLongestString);
                if (longestSubStringWithEvenVowels) {
                    previousLongestString = new StringBuffer(newSubString);
                }
            }
        }
        return previousLongestString.length();
    }

    public boolean longestSubString(StringBuffer newSubString, StringBuffer previousLongestString) {
        if (newSubString.length() > previousLongestString.length() && evenVowelCount(newSubString)) {
            return true;
        }
        return false;
    }

    public boolean evenVowelCount(StringBuffer str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
                count++;
            }
        }
        if (count % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}