/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.bitwise;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: SingleNumber.java, v 0.1 2020-04-01 21:35 paras.chawla Exp $$
 * Concept 1 -SWAPPING a ^= b; b ^= a; a ^= b
 * Concept 2  a^a=0 & a^0=a
 *
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 */
public class SingleNumber {

    public static void main(String[] args) {
        System.out.println(SingleNumber.singleNumber(new int[] {3, 4, 5, 3, 4}));
        //System.out.println(SingleNumber.findSingle(new int[] {3, 4, 5, 3, 4}));
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result = result ^ i;
        }
        return result;
    }

    public static int findSingle(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i : nums) {
            if (!set.remove(i)) {
                set.add(i);
            }
        }
            return set.iterator().next();
    }

}