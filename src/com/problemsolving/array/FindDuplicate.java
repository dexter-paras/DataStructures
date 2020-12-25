/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paras.chawla
 * @version $Id: FindDuplicate.java, v 0.1 2020-08-06 22:50 paras.chawla Exp $$
 * <p>
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * <p>
 * Find all the elements that appear twice in this array.
 * <p>
 * Input: [4,3,2,7,8,2,3,1]
 * <p>
 * Output: [2,3]
 */
public class FindDuplicate {

    // Assumption 1 ≤ a[i] ≤ n (n = size of array)
    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> result = new ArrayList<>();

        // if array is empty return empty list
        if (nums.length == 0) {
            return result;
        }

        // traverse array, assuming array[i] is greater than 0, so min idx we get from array[i]-1 is 0
        // idx   0 1 2 3 4
        // nums=[1,4,4,2,3]
        //     [-1 4 4 -2
        //     [-1  -4 2 ]

        for (int i = 0; i < nums.length; i++) {
            if (nums[Math.abs(nums[i]) - 1] < 0) {
                result.add(Math.abs(nums[i]));
            }
            nums[Math.abs(nums[i]) - 1] *= -1;
        }
        return result;
    }

    public int findDuplicate(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        // nums[] = [1,3,4,2,2]
        for (int i = 0; i < nums.length; i++) {

            if (nums[nums[i] - 1] > 0) {
                nums[nums[i] - 1] *= -1;
            } else {
                return nums[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new FindDuplicate().findDuplicate(new int[] {1, 3, 4, 2, 2});
    }
}