/**
 * Alipay.com Inc. Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.problemsolving.string;

import java.util.Arrays;

/**
 * @author paras.chawla
 * @version $Id: ThreeSumClosest.java, v 0.1 2021-04-22 9:32 AM paras.chawla Exp $$
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {

        // base condition
        if(nums.length <3)
            return -1;

        int ans = Integer.MAX_VALUE;
        int preDiff = Integer.MAX_VALUE;

        Arrays.sort(nums);

        for(int i=0;i < nums.length -2 ;i++){

            int j = i+1, k= nums.length-1;

            // Need to track smallest abs diff btw sum and target
            while(j < k){

                int sum = nums[i]+nums[j]+nums[k];
                int curDiff = Math.abs(sum - target);

                if(curDiff < Math.abs(preDiff)){
                    ans = sum;//-3
                    preDiff = curDiff;//4
                }

                if(sum< target) j++;
                else k--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ThreeSumClosest obj = new ThreeSumClosest();
        System.out.println(obj.threeSumClosest(new int[]{-1,2,1,-4},1));
        System.out.println(obj.threeSumClosest(new int[]{0,1,2},3));
    }
}