/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.Hashtable;

/**
 * @author paras.chawla
 * @version $Id: TwoSum.java, v 0.1 2019-08-06 23:14 paras.chawla Exp $$
 */
public class TwoSum {

    // array =[10,15,3,7]
    public int[] twoSum(int[] nums, int target) {

        Hashtable<Integer, Integer> hashTable = new Hashtable<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];//2
            if (hashTable.containsKey(target - num)) {
                return new int[] {hashTable.get(target - num), i};
            }
            hashTable.put(num, i);
        }
        throw new IllegalArgumentException("no two Solution");
    }

    public static void main(String[] args) {
        int array[] = {2, 7, 11, 15};
        int[] ind = new TwoSum().twoSum(array, 9);
        System.out.println(ind[0] + "\n" + ind[1]);
    }
}