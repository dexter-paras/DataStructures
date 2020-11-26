/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.string;

/**
 * @author paras.chawla
 * @version $Id: ReverseInteger.java, v 0.1 2019-08-13 22:32 paras.chawla Exp $$
 */
public class ReverseInteger {

    public int reverse(int x) {

        boolean negativeNum = false;
        int mod;
        int reverse = 0;
        if (x < 0) {
            negativeNum = true;
            x = -x;
        }

        while (x > 0) {
            mod = x % 10;
            reverse = reverse * 10 + mod;
            x = x / 10;
        }

        if (reverse > Integer.MAX_VALUE) {
            return 0;
        }
        return negativeNum ? -(int) reverse : (int) reverse;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInteger().reverse(1534236469));
        System.out.println(new ReverseInteger().reverse(-123));
        System.out.println(new ReverseInteger().reverse(120));
    }

}