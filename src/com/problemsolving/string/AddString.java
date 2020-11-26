/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.string;

/**
 * @author paras.chawla
 * @version $Id: AddString.java, v 0.1 2019-07-05 23:23 paras.chawla Exp $$
 */
public class AddString {

    public static void main(String[] args) {
        String s1 = "99975297";
        String s2 = "00923679";
        addStrings(s1, s2);
    }

    public static String addStrings(String num1, String num2) {
        int extra = 0;
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        for (; l1 >= 0 || l2 >= 0 || extra == 1; l1--, l2--) {
            int x = l1 < 0 ? 0 : num1.charAt(l1) - '0';
            int y = l2 < 0 ? 0 : num2.charAt(l2) - '0';
            sb.append((x + y + extra) % 10);
            extra = (x + y + extra) / 10;
        }
        return sb.reverse().toString();
    }
}