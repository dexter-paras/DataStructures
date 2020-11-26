/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bitwise;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: HappyNumber.java, v 0.1 2020-04-02 14:57 paras.chawla Exp $$
 */
public class HappyNumber {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = squareDigits(n);
        }
        return n == 1;
    }

    int squareDigits(int n) {
        int result = 0;

        for (; n != 0; n /= 10) {
            int module = n % 10;
            result += module * module;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new HappyNumber().isHappy(19));
    }
}