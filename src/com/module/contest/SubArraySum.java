/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.module.contest;

/**
 * @author paras.chawla
 * @version $Id: SubArraySum.java, v 0.1 2020-04-22 22:59 paras.chawla Exp $$
 */
public class SubArraySum {

    public int subarraySum(int[] nums, int k) {

        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new SubArraySum().subarraySum(new int[] {1, 3, 2, 1, 5, 4, 2}, 6));
        System.out.println(new SubArraySum().subarraySum(new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0));
    }
}