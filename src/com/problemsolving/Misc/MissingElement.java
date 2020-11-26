/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.problemsolving.Misc;

import java.util.HashSet;
import java.util.Set;

/**
 * @author paras.chawla
 * @version $Id: MissingElement.java, v 0.1 2020-07-27 23:32 paras.chawla Exp $$
 */
public class MissingElement {

    //            nums[]->4,7,11,15,20,30 & k=10
    //                              ^
    // Missing elements-->5,6,8,9,10,12,13,14,16,17,18,19,21---29
    public int missingElement(int[] nums, int k) {
        int currCount = 0;
        int prevCount;
        int missCount = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 1) {
                missCount = nums[i] - nums[i - 1] - 1;//4
            }
            prevCount = currCount; //8
            currCount += missCount;//12
            if (currCount >= k) { // 12>=10
                return nums[i - 1] + k - prevCount;
            }
        }
        return nums[nums.length] + k - currCount;

    }

    public int missingElementDp(int[] nums, int k) {

        // create miss array(dp array) from nums
        int[] miss = new int[nums.length];
        miss[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            miss[i] = miss[i - 1] + nums[i] - nums[i - 1] - 1;
            if (miss[i] >= k) {
                return nums[i - 1] + k - miss[i - 1];
            }
        }
        return nums[nums.length - 1] + k - miss[miss.length - 1];
    }

    public int missingElementSet(int[] nums, int k) {
        int n = nums.length;
        int s = nums[0];
        int e = nums[n-1];
        Set<Integer> set = new HashSet<>();
        for(int i : nums) {
            set.add(i);
        }
        for(int i=s; i<=e; i++) {
            if(!set.contains(i)) {
                if(k == 1)
                    return i;
                else
                    k--;
            }
        }
        return k+e;
    }

    public static void main(String[] args) {
        new MissingElement().missingElementSet(new int[]{4,7,11,15,20,30},10);
    }

}