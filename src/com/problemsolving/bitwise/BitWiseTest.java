/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bitwise;

/**
 * @author paras.chawla
 * @version $Id: BitWiseTest.java, v 0.1 2020-04-01 21:07 paras.chawla Exp $$
 */
public class BitWiseTest {

    public static void main(String[] args) {
        // if any bit is 1 , it gives 1 else gives 0
        System.out.println(5 | 7);

        // if any both bits are 1 ,it gives 1 else 0
        System.out.println(5 & 7);

        // if corresponding bits are different, it gives 1, else it gives 0
        System.out.println(5 ^ 7);

        // XOR of number with its own is always 0
        System.out.println(5 ^ 5);

        // it makes every 0 to 1, and every 1 to 0
        System.out.println(~10);

        // signed right shift operator
        System.out.println(10 >> 1);
        System.out.println(10 >> 2);

        // signed left shift operator , Multiply
        System.out.println(10 << 3);

        System.out.println(1 << 5);

    }
}