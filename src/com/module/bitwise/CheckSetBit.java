/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.bitwise;

/**
 * @author paras.chawla
 * @version $Id: CheckSetBit.java, v 0.1 2020-09-16 01:08 paras.chawla Exp $$
 */
public class CheckSetBit {

    public static void main(String[] args) {

        // to find if kth bit set for num
        int k = 3;
        int num = 5;//00000101;

        int mask = 1;
        // bit mask k times
        mask <<= k-1; // 00000100;

        if ((mask & num) == 1) {
            System.out.println(k + "th bit set for num" + num);
        }
        isKthBitSet(5,3);
    }

    static void isKthBitSet(int n,
                            int k)
    {
        if (((n >> (k - 1)) &
                1) == 1)
            System.out.println("SET");
        else
            System.out.println("NOT SET");
    }

}