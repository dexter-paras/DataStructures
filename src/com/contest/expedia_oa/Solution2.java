/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.contest.expedia_oa;

/**
 * @author paras.chawla
 * @version $Id: Solution2.java, v 0.1 2020-12-10 20:44 paras.chawla Exp $$
 */
public class Solution2 {

    // Compression of a String
    public static String compressedString(String message) {
        char[] chars = message.toCharArray();

        int count = 1;
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            chars[j++] = chars[i];
            while (i < chars.length - 1 && chars[i] == chars[i + 1]) {
                count++;
                i++;
            }
            if (count != 1)
                if (count > 9) {
                    String str = String.valueOf(count);
                    for (char ch:str.toCharArray()) {
                        chars[j++] = ch;
                    }
                }
                else
                    chars[j++] = Character.forDigit(count, Character.MAX_RADIX);
            count = 1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<j; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(compressedString("abaasass"));
    }

}