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
        //System.out.println(oneCount);
        return oneCount;
    }

    /* Approach 2 - lets say n= 011101
    https://www.youtube.com/watch?v=wLHhAHkID9M
    Intuition - to make LSB of num to 0 by having n & n-1
    This will keep making LSB of num to 0, increment count till all LSB of num becomes 0 ~ num becomes 0
        n  =  011101
        n-1=  011100
     ---------------
     n & n-1= 011100
     ---------------

        n  =  011100
        n-1=  011011
     ---------------
     n & n-1= 011000
     ---------------

        n  =  011000
        n-1=  010111
     ---------------
     n & n-1= 010000
     ---------------

        n  =  010000
        n-1=  001111
     ---------------
     n & n-1= 000000
     ---------------

     */
    public int hammingWeightApproach2(int n) {

        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Number1Bits().hammingWeightApproach2(1011));
    }
}