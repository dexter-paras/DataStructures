/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.contest;

/**
 * @author paras.chawla
 * @version $Id: Base62.java, v 0.1 2021-01-06 6:44 PM paras.chawla Exp $$
 */
public class Base62 {

    public static void main(String[] args) {

        Integer num = Integer.MAX_VALUE;

        String str = "0123456789abcdefghijklmnopqrstuvqxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char[] chars = str.toCharArray();

        String hash_str = "";
        while (num > 0) {
            hash_str += chars[num % 62];
            num /= 62;
        }
        System.out.println(hash_str);

    }

}