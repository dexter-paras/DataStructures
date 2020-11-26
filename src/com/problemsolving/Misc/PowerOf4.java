/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Misc;

/**
 * @author paras.chawla
 * @version $Id: PowerOf4.java, v 0.1 2020-08-05 10:46 paras.chawla Exp $$
 */
public class PowerOf4 {

    // Approach 1 - Bad solution
    public boolean isPowerOfFour(int num) {
        for (; num > 0; num /= 4) {
            if (num % 4 == 0) { return true; }
        }
        return false;
    }

    // Approach 2 - Good solution
    public boolean isPowerOfFourSol2(int num) {
        double temp = num;
        for (; temp > 0; temp /= 4) {
            if (temp == 1) { return true; }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new PowerOf4().isPowerOfFour(5));
    }
}