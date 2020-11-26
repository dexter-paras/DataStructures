/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bitwise;

/**
 * @author paras.chawla
 * @version $Id: BitWiseAndRange.java, v 0.1 2020-04-24 10:14 paras.chawla Exp $$
 */
public class BitWiseAndRange {

    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            count += 1;
        }
        return m <<= count;
    }
}