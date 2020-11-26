/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Misc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: SingleNumber.java, v 0.1 2020-07-23 23:32 paras.chawla Exp $$
 */
public class SingleNumber {

    //[1,2,1,3,2,5]
    private int[] singleNumber(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int key : nums) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        // Exactly 2 numbers with count as 1
        int[] result = new int[2];
        int cnt = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                result[cnt++] = entry.getKey();
            }
        }
        return result;
    }

    // Approach 2- Amazing Art
    // https://leetcode.com/problems/single-number-iii/discuss/68901/Sharing-explanation-of-the-solution
    // https://leetcode.com/problems/single-number-iii/discuss/68922/A-summary-of-Java-solutions
    // Pseudo Code
    /*
    1. first run ^ to get the a^b
    2. search the position to do partitioning in binary representation of a^b
    3. partition the array by this position and get a and b correspondingly
    */
    private int[] singleNumberUsingBitwise(int[] nums) {

        // Only 2 unique numbers, others are in duplicate pairs
        int[] result = new int[2];

        int num1 = 0; // Group1 calculation variable
        int num2 = 0; // Group2 calculation variable

        // 1. XOR all and find result
        int xor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xor ^= nums[i];
        }

        // 2. Find index with 1 as bit in final xor calculated .
        // This index position will help in partioning array in 2 groups and hence non-duplicate elements

        // 2.1 To Calculate lowest 1 as bit
        // int bit = xor & -xor;

        // 2.2 To Calculate heighest 1 as bit
        int bit = Integer.highestOneBit(xor);

        // 3. Scan array and group array based on above calculated bit at index i
        for (int num : nums) {
            if ((num & bit) > 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }

        //4. num1 is first unique element of group1 and num2 as second unique element of group2
        result[0] = num1;
        result[1] = num2;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumber().singleNumber(new int[] {1, 2, 1, 3, 2, 5}));
        System.out.println(new SingleNumber().singleNumberUsingBitwise(new int[] {1, 2, 1, 3, 2, 5}));
    }
}