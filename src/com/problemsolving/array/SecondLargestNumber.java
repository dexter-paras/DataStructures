/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.array;

/**
 * @author paras.chawla
 * @version $Id: SecondLargestNumber.java, v 0.1 2021-01-02 11:31 AM paras.chawla Exp $$
 */
public class SecondLargestNumber {

    public static void main(String[] args) {
        
        int[] nums = new int[]{-1,7,2,-5,11,8,16,22,87,87,129,1241};

        System.out.println(secondLargestNumber(nums));
    }

    private static int secondLargestNumber(int[] nums) {

        if(nums ==null || nums.length==0){
            return -1;
        }

        if(nums.length==1){
            return nums[0];
        }

        int first = nums[0] > nums[1] ? nums[0]: nums[1];
        int second = nums[0] > nums[1] ? nums[1]: nums[0];

        for(int i=2;i<nums.length;i++) {

            int element = nums[i];
            if (element < first && element > second) {
                second = element;
            } else if (element > first) {
                second = first;
                first = element;
            }
        }

        return second;
    }

}