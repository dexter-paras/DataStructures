/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: ReverseWords.java, v 0.1 2020-07-16 09:34 paras.chawla Exp $$
 * @see 151. https://leetcode.com/articles/reverse-words-in-a-string/
 * @action Need to Practice Approach 2 and Approach 3
 */
public class ReverseWords {

    public static void main(String[] args) {
        String str = "      a  good     example  ";
        System.out.println((reverseWords(str)));
        System.out.println((reverseWordsBuildIn(str)));
    }

    // Approach 1 - Without using any build-in function
    private static String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();

        List<String> list = new ArrayList<>();

        int length = s.toCharArray().length;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) != ' ') {
                builder.append(s.charAt(i));
                if (i == length - 1) {
                    list.add(builder.toString());
                }
            } else if (s.charAt(i) == ' ' && builder.length() != 0) {
                list.add(builder.toString());
                builder = new StringBuilder();
            }
        }

        StringBuilder reverseString = new StringBuilder();
        for (int i = list.size(); i > 0; i--) {
            if (i > 1) {
                reverseString.append(list.get(i - 1)).append(" ");
            } else {
                reverseString.append(list.get(i - 1));
            }
        }
        return reverseString.toString();
    }

    // Approach 2 - Split + Reverse + Join
    private static String reverseWordsBuildIn(String s) {

        // remove leading and trailing spaces
        s = s.trim();

        // split by multiple spaces
        List<String> wordList = Arrays.asList(s.split("\\s+"));

        // reverse the collection
        Collections.reverse(wordList);

        //join with whitespace
        return String.join(" ", wordList);
    }
}