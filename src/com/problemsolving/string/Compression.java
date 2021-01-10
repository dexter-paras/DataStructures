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

    // [a,a,b,b,c,c,c] TODO
    public int compressSol2(char[] chars) {

        return -1;
    }

    public static void main(String[] args) {
        //System.out.println(compress(new char[]{'a','a','b','b','c','c','c'}));
        System.out.println(compress(new char[] {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
    }
}