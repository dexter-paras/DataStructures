/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.array;

/**
 * @author paras.chawla
 * @version $Id: MoveZeroes.java, v 0.1 2020-04-04 20:16 paras.chawla Exp $$
 */

public class MoveZeroes {

    // nums[]= [7,0,11,4,0,21,3]
    // Move zeros to left
    private static void moveZeroes(int[] nums) {

        //count of non-negative numbers
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[count++] = nums[i];
            }
        }

        //Fill remaining elements with 0
        while (count < nums.length) {
            nums[count++] = 0;
        }
    }

    public static void moveZeroes2(int[] nums) {
        int zeroInd=-1;

        for(int i=0;i<nums.length;i++){
            if (nums[i]==0 && zeroInd<0){
                zeroInd=i;
            }else if(nums[i]!=0 && zeroInd>=0){
                nums[zeroInd]=nums[i];
                nums[i]=0;
                zeroInd++;
            }
        }
    }

    public static void main(String[] args) {
        MoveZeroes.moveZeroes2(new int[] {0, 0, 1});
    }
}