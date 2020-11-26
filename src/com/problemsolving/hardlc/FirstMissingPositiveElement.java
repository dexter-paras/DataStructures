/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.hardlc;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: FirstMissingPositiveElement.java, v 0.1 2020-10-24 19:30 paras.chawla Exp $$
Given an unsorted integer array nums, find the smallest missing positive integer.
Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space.?
Example 1:

Input: nums = [1,2,0]
Output: 3
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1

 Amazing Explanation
https://leetcode.com/problems/first-missing-positive/discuss/17214/Java-simple-solution-with-documentation

 Thought Process
https://leetcode.com/problems/first-missing-positive/discuss/319270/Explanation-of-crucial-observation-needed-to-deduce-algorithm
 */
public class FirstMissingPositiveElement {

    public int firstMissingPositive(int[] nums) {
        int[] array = new int[nums.length];

        Arrays.fill(array,Integer.MIN_VALUE);

        for(int i=0;i<nums.length;i++){
            int idx=nums[i]-1;
            if(idx>=0 && idx<nums.length){
                array[idx]=nums[i];
            }
        }

        int i;
        for(i=0;i<array.length;i++){
            if(array[i]==Integer.MIN_VALUE){
                break;
            }
        }
        return i+1;
    }
}