/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.string;

/**
 * @author paras.chawla
 * @version $Id: Compression.java, v 0.1 2020-12-30 12:00 PM paras.chawla Exp $$
 */
public class Compression {

    public static int compress(char[] chars) {

        if (chars == null || chars.length == 0) {
            return 0;
        }

        StringBuilder builder = new StringBuilder();

        //chars = [a,a,b,b,c,c,c]
        for (int i = 0; i < chars.length; i++) {

            char ch = chars[i];
            int count = 1;
            int j;
            for (j = i + 1; j < chars.length; j++) {
                if (ch != chars[j]) {
                    break;
                }
                count++;
            }

            builder.append(ch);
            if (count > 1) {
                builder.append(count);
            }

            i = j - 1;
        }

        chars = new char[builder.length()];
        int indexAns = 0;
        for (char c : builder.toString().toCharArray()) { chars[indexAns++] = c; }
        return builder.length();
    }

    // Workging but Not In-place
    public static int compressSol2(char[] chars) {

        int i = 0, j = 0, idxAns = 0;
        while (i < chars.length) {

            int cnt = 0;
            while (j < chars.length && chars[i] == chars[j]) {
                cnt++;
                j++;
            }

            chars[idxAns++] = chars[i];

            if (cnt != 1) {
                for (char c : Integer.toString(cnt).toCharArray()) { chars[idxAns++] = c; }
            }
            i = j;
        }
        return idxAns;
    }

    // WORKING as per Problem Statement
    public static int compressSol3(char[] chars) {
        int indexAns = 0, index = 0;
        while (index < chars.length) {
            char currentChar = chars[index];
            int count = 0;
            while (index < chars.length && chars[index] == currentChar) {
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if (count != 1) { for (char c : Integer.toString(count).toCharArray()) { chars[indexAns++] = c; } }
        }
        return indexAns;
    }

    public static void main(String[] args) {
        System.out.println(compressSol3(new char[] {'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        System.out.println(compressSol3(new char[] {'a'}));
        System.out.println(compressSol3(new char[] {'a', 'b', 'c'}));
        System.out.println(compressSol3(new char[] {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
    }
}