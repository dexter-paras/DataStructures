/**
 * Alipay.com Inc. Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author paras.chawla
 * @version $Id: TwoSum.java, v 0.1 2019-08-06 23:14 paras.chawla Exp $$
 */
public class TwoSum {

    // array =[10,15,3,7] BETTER APPROACH
    public int[] twoSum(int[] nums, int target) {

        Hashtable<Integer, Integer> hashTable = new Hashtable<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (hashTable.containsKey(target - num)) {
                return new int[] {hashTable.get(target - num), i};
            }
            hashTable.put(num, i);
        }
        throw new IllegalArgumentException("no two InMemoryDataStructure");
    }

    // TRAVESSING TWICE
    public int[] twoSum2(int[] nums, int target) {

        int[] result = new int[2];

        // base condition
        if (nums == null) {
            return result;
        }

        // add into map <Value,Index>
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], i);
        }

        // traversing again
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int array[] = {3, 3};
        int[] ind = new TwoSum().twoSum2(array, 6);
        System.out.println(ind[0] + "\n" + ind[1]);
    }
}