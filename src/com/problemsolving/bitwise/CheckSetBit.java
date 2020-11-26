/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bitwise;

/**
 * @author paras.chawla
 * @version $Id: CheckSetBit.java, v 0.1 2020-09-16 01:08 paras.chawla Exp $$
https://www.geeksforgeeks.org/check-whether-bit-given-position-set-unset/
 */
public class CheckSetBit {

    public static void main(String[] args) {

        // to find if kth bit set for num
        int k = 2;

        //num = 00001010;
        int num = 10;

        // shift kth bit of num to 1st place
        num = num >> (k - 1);

        //now kth bit represent 1st bit of current num
        System.out.println(num & 1);
    }
}