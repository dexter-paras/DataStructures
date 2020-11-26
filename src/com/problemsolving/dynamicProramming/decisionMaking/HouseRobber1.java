/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.dynamicProramming.decisionMaking;

/**
 * @author paras.chawla
 * @version $Id: HouseRobber1.java, v 0.1 2020-03-28 17:03 paras.chawla Exp $$
 */
public class HouseRobber1 {

    // Bottom up Approach + Memoization
    public int rob(int[] nums) {

        // base conditions
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        //[2,1,1,2,3,1,4]
        int maxRobTillSecondLastHouse = nums[0]; //2
        int maxRobTillLastHouse = Math.max(nums[0], nums[1]);//2
        int maxRob = 0;
        for (int i = 2; i < nums.length; i++) {
            maxRob = Math.max(maxRobTillLastHouse, nums[i] + maxRobTillSecondLastHouse);
            maxRobTillSecondLastHouse = maxRobTillLastHouse;
            maxRobTillLastHouse = maxRob;
        }
        return maxRob;
    }

    private int robRecursive(int[] nums) {
        return rob(nums, nums.length - 1);
    }

    // At any current index, we can rob either 1) currentHouse + house before previous house 2) previous house
    // Recursive - Top Down solution
    private int rob(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        return Math.max(rob(nums, i - 1), nums[i] + rob(nums, i - 2));
    }

    public static void main(String[] args) {
        System.out.println(new HouseRobber1().rob(new int[] {2, 1, 1, 2, 3, 1, 4}));
        System.out.println(new HouseRobber1().robRecursive(new int[] {2, 1, 1, 2, 3, 1, 4}));
    }
}