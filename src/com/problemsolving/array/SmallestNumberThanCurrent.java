/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: SmallestNumberThanCurrent.java, v 0.1 2020-03-01 08:02 paras.chawla Exp $$
 */
public class SmallestNumberThanCurrent {

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] newArray= new int[nums.length];
        for(int i=0;i<nums.length;i++){
            int count=0;
            for(int j=0;j<nums.length;j++){
                if(nums[i]>nums[j]){
                    count++;
                }
            }
            newArray[i]=count;
        }
        return newArray;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{8,1,2,2,3})));
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{6,5,4,8})));
        System.out.println(Arrays.toString(smallerNumbersThanCurrent(new int[]{7,7,7,7})));
    }

}