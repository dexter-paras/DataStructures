/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.recursion;

/**
 * @author paras.chawla
 * @version $Id: JumpGame.java, v 0.1 2020-04-26 11:30 paras.chawla Exp $$
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        return jump(0, nums);
    }

    private boolean jump(int i, int[] nums) {

        int element = nums[i];

        if (i > nums.length) {
            return false;
        }

        // base conditions
        if (i == nums.length - 1) {
            return true;
        }
        if (element == 0 && i < nums.length - 1) {
            return false;
        }

        for (int j = 1; j <= element; j++) {
            return jump(i + j, nums);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame().canJump(new int[] {2, 0,0}));
        //System.out.println(new JumpGame().canJump(new int[] {3, 2, 1, 0, 4}));
    }
}