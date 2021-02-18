/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.string;

/**
 * @author paras.chawla
 * @version $Id: AddString.java, v 0.1 2019-07-05 23:23 paras.chawla Exp $$
 */
public class AddString {

    public static void main(String[] args) {
        String s1 = "12341";
        String s2 = "949";
        System.out.println(addStrings(s1, s2));
    }

    // "1" num1
    // "9" num2
    //"10" result
    public static String addStrings(String num1, String num2) {
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;

        StringBuilder builder = new StringBuilder();
        int extra = 0;
        for (; l1 >= 0 || l2 >= 0; l1--, l2--) {
            int sum = (l1 >= 0 ? num1.charAt(l1) - '0' : 0) + (l2 >= 0 ? num2.charAt(l2) - '0' : 0) + extra;//10
            extra = sum > 9 ? sum / 10 : 0;//1
            sum = sum > 9 ? sum % 10 : sum;//0
            builder.append(sum);//0
        }
        if (extra > 0) {
            builder.append(extra);
        }
        return builder.reverse().toString();

    }
}