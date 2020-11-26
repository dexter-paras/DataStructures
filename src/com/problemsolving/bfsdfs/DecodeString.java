/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bfsdfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: DecodeString.java, v 0.1 2020-11-25 00:55 paras.chawla Exp $$
 */
public class DecodeString {

    public static String decodeString(String s) {

        List<Character> result = new ArrayList<>();

        List<Integer> openBracIndexList = new ArrayList();

        for (char ch : s.toCharArray()) {

            if (ch == '[') {
                result.add(ch);
                openBracIndexList.add(result.size() - 1);
            } else if (ch == ']') {

                // construct string
                int currIdx = result.size();
                int lastIdx = openBracIndexList.get(openBracIndexList.size() - 1);

                StringBuilder builder = new StringBuilder();
                for (int a = lastIdx + 1; a < currIdx; a++) {
                    builder.append(result.get(a));
                }

                // find multiplication factor
                int multiplicationFactor = result.get(lastIdx - 1) - '0';
                int newStartIdx = lastIdx - 1;
                List<Character> newResult = new ArrayList<>();

                for (int i = 0; i < newStartIdx - 1; i++) {
                    newResult.add(result.get(i));
                }

                result = newResult;

                while (multiplicationFactor-- > 0) {
                    for (char c : builder.toString().toCharArray()) {
                        result.add(c);
                    }
                }
                openBracIndexList.remove(openBracIndexList.size() - 1);
            } else {
                result.add(ch);
            }
        }

        StringBuilder builder = new StringBuilder();
        for (char ch : result) {
            builder.append(ch);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        //System.out.println(decodeString("3[a]2[bc]"));
        //System.out.println(decodeString("abc3[cd]xyz"));
        //System.out.println(decodeString("2[abc]3[cd]ef"));
        System.out.println(decodeString("3[a2[c]]"));
    }

}