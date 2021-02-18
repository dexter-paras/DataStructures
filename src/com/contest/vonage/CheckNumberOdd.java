/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.contest.vonage;

/**
 * @author paras.chawla
 * @version $Id: CheckNumberOdd.java, v 0.1 2021-01-17 10:17 PM paras.chawla Exp $$
 */
public class CheckNumberOdd {

    public static void main(String[] args) {
        int x = 5,y=6;
        // 0101 -> 0010

        /*if(x&1){

        }*/
        if (x >> 1 == 0) {
            System.out.println("Odd");
        }

        if (y >> 1 == 0) {
            System.out.println("Even");
        }
    }
}