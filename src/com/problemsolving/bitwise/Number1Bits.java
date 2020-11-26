/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bitwise;

/**
 * @author paras.chawla
 * @version $Id: Number1Bits.java, v 0.1 2020-04-14 19:35 paras.chawla Exp $$
 */
public class Number1Bits {

    public int hammingWeight(int n) {
        int oneCount = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                oneCount++;
            }
            mask <<= 1;
        }
        System.out.println(oneCount);
        return oneCount;
    }

    public static void main(String[] args) {
        new Number1Bits().hammingWeight(11111111);
    }
}